#To Get all products
http://localhost:8080/AllProducts

#To Delete using sku
http://localhost:8080/Delete/zxcvbn12

#To get a specific product by sku
http://localhost:8080/GetProduct/abcde123

#Search products based on sku or color or size or brand 
http://localhost:8080/search?sku=abcde123&color=white&brand=Asics&size=L&price

#Find total available product for a seller
http://localhost:8080/ProductsBySeller/Magic co. Ltd.

#To order a product
http://localhost:8080/Order/bbcde133

#Add a product
http://localhost:8080/AddProduct
Body - raw JSON
       {
            "product_id": 10,
            "sku": "abcde123",
            "name": "Asics gel Kayana 26",
            "category": "Shoe",
            "brand": "Asics",
            "description": "Running shoe",
            "seller_name": "Magic co. Ltd.",
            "stock": 12,
            "price": 8999.0,
            "created_datetime": "2020-01-18T06:40:35.479+0000",
            "modified_datetime": "2020-01-18T06:40:35.479+0000",
            "spec": [
                {
                    "sprec_id": 1,
                    "sku": "abcde123",
                    "attribute": "color",
                    "value": "white"
                },
                {
                    "sprec_id": 2,
                    "sku": "abcde123",
                    "attribute": "size",
                    "value": "29.5"
                }
            ]
        }