import pymysql
import matplotlib
matplotlib.use('TkAgg')  # 尝试使用 TkAgg 后端
# 你也可以尝试 'Qt5Agg' 或 'GTK3Agg'
import matplotlib.pyplot as plt
from io import BytesIO
from PIL import Image

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

def get_image_data(product_id):
    try:
        with connection.cursor() as cursor:
            sql = "SELECT image_data FROM products_lowest_price WHERE product_id = %s"
            cursor.execute(sql, (product_id,))
            result = cursor.fetchone()
            if result:
                return result[0]
            else:
                print("No image data found for the given product_id.")
                return None
    except Exception as e:
        print(f"An error occurred: {e}")
        return None

def display_image(image_data):
    if image_data:
        # 使用BytesIO将二进制数据转换为文件类对象
        image_file = BytesIO(image_data)
        # 使用PIL打开图片
        image = Image.open(image_file)
        # 使用matplotlib展示图片
        plt.imshow(image)
        plt.axis('off')  # 不显示坐标轴
        plt.show()

def main():
    product_id = 70 # 替换为你想要查询的product_id
    image_data = get_image_data(product_id)
    display_image(image_data)

if __name__ == "__main__":
    main()