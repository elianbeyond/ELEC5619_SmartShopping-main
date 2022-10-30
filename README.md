# ELEC5619_SmartShopping

## Used libraries and their versions

| Dependency(groupID/artifactId)         | Version   |
|----------------------------------------|-----------|
| org.springframework.boot               | 2.7.3     |
| org.projectlombok / lombok             |           |
| cn.jpush.api / jpush-client            | 3.4.8     |
| com.alibaba / fashjson                 | 1.2.76    |
| org.daisy.libs/ commons-httpclient     | 3.1.0     |
| com.alibaba / druid                    | 1.0.29    |
| org.apache.httpcomponents / httpclient | 4.5.6     |
| dom4j / dom4j                          | 1.6.1     |
| redis.clients / jedis                  | 2.9.0     |
| mysql(include mysql-connector-java)    | &geq; 5.7 |

#### Back-end dependencies can be found in /server/pom.xml

| Dependency              | Version |
|-------------------------|---------|
| vue                     | 3.0.0   |
| vue-cookies             | 1.8.0   |
| vue-router              | 4.0.0   |
| vue-universal-modal     | 1.1.3   |
| vuex                    | 4.0.0   |
| vuex-persistedstate     | 4.1.0   |
| md5                     | 2.3.0   |
| save                    | 2.5.0   |
| core-js                 | 3.6.5   |
| axios                   | 0.26.1  |
| element-plus            | 2.2.16  |
| @fawmi/vue-google-maps  | 0.9.79  |

 

#### Front-end dependencies can be found in /frontend/package.json

## Working functionalities of the project

### User
#### User authentication
- When the user wants to log in, the user needs to enter the user name and password and submit to the backend for verification
- After user login, this user will get a token. We will use this token to verify this user.


#### Normal user
- User can register, login and logout
- User will play the role of both buyer and seller
- User can change the profile
- User can manage the shipping address

#### Buyer
- Search products by using search bar
- Add items to cart
- Use discount when they pay

#### Seller
- Sellers can rent warehouses
- Sellers can see the warehouse location by GoogleMap API
- Sellers can set discount
- Add new item for sale

### Cart

#### cart
- User can add and remove items to the shopping cart
- When user logout, the shopping cart will be emptied

#### Checkout
- Predict logistics time for buyers
- Checkout
- Use the voucher code when paying



## A quick guide on how to run our application
### Database:
We use MySQL as the back-end database. 
Before running, you need to install mysql and configure your database in server/src/main/resources/application.yml, 
including changing to your username and password. If you don't have a database, you can run backend.sql, 
which will automatically help you create a database and insert some of the data for testing.
## Warning:
If you run the files in server/src/test for testing, it may modify the data in the database causing subsequent file tests to fail. 
Thus, when a test fails, it may be because a previous test has already modified the data in the database.


### Frontend:
We use Vue.js as presentation layer, in order to compile and run the project successfully, you should make sure the **NPM** has been installed successfully in your computer.

1. cd frontend
2. npm install
3. npm run server

The server will be run on localhost:8081

### Backend
We use SpringBoot in the backend, firstly, you need to download the project backend dependencies by using maven. The server will be run on localhost:8080

#### After running frontend and backend, we can open website localhost:8081/main#/home
