import pymysql
import matplotlib
matplotlib.use('TkAgg')  # 尝试使用 TkAgg 后端
import matplotlib.pyplot as plt
from datetime import datetime
import io

# 数据库配置信息
db_config = {
    'host': 'localhost',
    'user': 'root',  # 替换为你的MySQL用户名
    'password': '040826',  # 替换为你的MySQL密码
    'db': 'commodity_data_info',  # 数据库名
    'charset': 'utf8mb4',
}

# 连接数据库
connection = pymysql.connect(**db_config)


def get_price_history(product_id):
    try:
        with connection.cursor() as cursor:
            sql = """
            SELECT date, price FROM price_history
            WHERE product_id = %s
            ORDER BY date ASC;
            """
            cursor.execute(sql, (product_id,))
            return cursor.fetchall()
    except Exception as e:
        print(f"An error occurred: {e}")


def get_lowest_price(product_id):
    price_history_data = get_price_history(product_id)
    if price_history_data:
        return min(price_history_data, key=lambda x: float(x[1]))
    return None


def plot_price_history(product_id):
    price_history_data = get_price_history(product_id)
    if not price_history_data:
        return None

    dates = []
    lowest_prices = []

    for date_str, price in price_history_data:
        date_str = datetime.strftime(date_str, '%Y-%m-%d %H:%M:%S')
        date = datetime.strptime(date_str, '%Y-%m-%d %H:%M:%S')
        dates.append(date)
        lowest_price_data = get_lowest_price(product_id)
        if lowest_price_data:
            lowest_price = lowest_price_data[1]
            lowest_prices.append(lowest_price)

    plt.figure(figsize=(10, 5))
    plt.plot(dates, lowest_prices, marker='o', linestyle='-')
    plt.grid(False)
    plt.title(f'商品历史价格 ID: {product_id}')
    plt.xlabel('时间')
    plt.ylabel('最低价')

    # 设置中文字体
    plt.rcParams['font.sans-serif'] = ['SimHei']  # Windows系统可用
    plt.rcParams['axes.unicode_minus'] = False  # 正确显示负号

    # 将图表保存到内存中的IO对象
    buf = io.BytesIO()
    plt.savefig(buf, format='png')
    plt.close()
    buf.seek(0)

    # 返回图像的二进制数据
    return buf.getvalue()


def save_lowest_price_and_image(product_id):
    lowest_price_data = get_lowest_price(product_id)
    if lowest_price_data:
        lowest_price = lowest_price_data[1]
        image_data = plot_price_history(product_id)

        if image_data:
            try:
                with connection.cursor() as cursor:
                    # 检查products_lowest_price表中是否有数据
                    cursor.execute("SELECT COUNT(*) FROM products_lowest_price WHERE product_id = %s", (product_id,))
                    count = cursor.fetchone()[0]

                    if count == 0:
                        # 没有数据，插入新记录
                        sql = """
                        INSERT INTO products_lowest_price (product_id, lowest_price, image_data)
                        VALUES (%s, %s, %s)
                        """
                        cursor.execute(sql, (product_id, lowest_price, image_data))
                        connection.commit()
                        print(f"Inserted new record for product ID {product_id}")
                    else:
                        # 有数据，更新记录
                        sql = """
                        UPDATE products_lowest_price
                        SET lowest_price = %s, image_data = %s
                        WHERE product_id = %s
                        """
                        cursor.execute(sql, (lowest_price, image_data, product_id))
                        connection.commit()
                        print(f"Updated record for product ID {product_id}")
            except Exception as e:
                print(f"An error occurred: {e}")
    else:
        print(f"No price history found for product ID {product_id}")


def main():
    # 假设我们要处理的product_id范围
    for product_id in range(501, 1001):  # 例如从1到99
        save_lowest_price_and_image(product_id)


if __name__ == "__main__":
    main()
