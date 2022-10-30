<template>
  <div>
    <p class="price">Total Price: ${{ this.current_price }}</p>
    <p class="price">Select Voucher</p>
    <select id="select" v-model="category">
      <option value="10percent off">{{Voucher1}}</option>
      <option value="10 dollar off">{{Voucher2}}</option>
    </select>
    <button class="button action-button" id="use" v-on:click="use">
          Use
    </button>
    <div class="buttons">
      <button class="button action-button" v-on:click="purchase">Purchase</button>
    </div>
    <table>
      <thead class="head">
        <tr>
          <th class="padding-col"></th>
          <th class="product-col">Product</th>
          <th class="price-col">Price</th>
          <th class="times-col"></th>
          <th class="quantity-col">Quantity</th>
          <th></th>
          <th class="padding-col"></th>
        </tr>
      </thead>
      <tbody v-for="(item, index) in this.data" :key="index" class="cart">

        <tr class="detail">
          <td></td>
          <td>
            <div class="product-title">{{ item.productName }}</div>
          </td>
          <td>${{ item.price }}</td>
          <td>×</td>
          <td>{{ item.quantity }}</td>
          <td> <button class="button action-button delete" v-on:click="deleteProduct(item)">delete</button></td>
          <td></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      data: [],
      name: [],
      price: [],
      quantity: [],
      current_price: "",
      category:"",
      productId:"",
      Voucher1:"",
      Voucher2:"",
      used: false,
      
      
    };
  },
  mounted() {
    this.getData();
    this.getVoucher();
  },
  methods: {
    getData() {
      console.log(sessionStorage);
      axios
        .post("api/cart/getCart", {
          userId: Number(sessionStorage.getItem("userId")),
        })
        .then((response) => {
          console.log(response);
          console.log(response.data);
          this.data = response.data;
          this.computeTotal();
        });
    },
    getVoucher(){
      axios.post("api/cart/getVoucher",{
        userId: Number(sessionStorage.getItem("userId")),
      }).then((response =>{
        console.log("Voucher")
        console.log(response)
        if(response.data[0]==null){
          this.Voucher1 = null;
          this.Voucher2 = null;
        }else{
          this.Voucher1 = response.data[0].name
          if(response.data[1]==null){
           this.Voucher2 = null
          }else{
            this.Voucher2=response.data[1].name
          }
          
        }

      }))
    },
    computeTotal() {
      let total = 0;
      for (let i = 0; i < this.data.length; i++) {
        total += this.data[i].price * this.data[i].quantity;
      }
      console.log(total);
      this.current_price = total;
    },
    purchase() {
      axios.get("/api/cart/purchase",{
        params:{
          userId : Number(sessionStorage.getItem("userId")),
          voucher: this.category 
        }
      }).then((response =>{
        console.log(response)
        console.log("category is" + this.category)
        alert(response.data.msg)
        window.location.reload()
      }))
    },
    deleteProduct(item){
      console.log("deleteProduct");
      console.log(item.cartId);
      axios.post("/api/cart/deleteProduct",{
        cartId:item.cartId
      }).then(response =>{
        console.log(response)
        window.location.reload()
      })
    },

    use(){
      console.log(this.used1 + this.used2)
      if(this.category=="10 dollar off"){
        console.log("Voucher2 is " + this.Voucher1)
        if(this.Voucher2 == null){
          alert("no available voucher!");
        }else{
          if(this.used==false){
            this.current_price -= 10;
            this.used = true;
          }else{
            alert("You already used the voucher")
          }
          
        }
        
      }if(this.category=="10percent off"){
        console.log("Voucher1 is " + this.Voucher1)
        if(this.Voucher1 == null){
          alert("no available voucher!");
        }else{
          if(this.used==false){
            this.current_price = this.current_price*0.9;
            this.used =true
          }else{
            alert("You already used the voucher")
          }
          
        }
        
      }
    }
  },
};
</script>

<style>
.price {
  width: 75rem;
  text-align: left;
  font-family: "Lato";
  font-size: 1.5rem;
  margin: 0;
}
table {
  width: 75rem;
  table-layout: fixed;
  border-collapse: separate;
  border-spacing: 0;
}

.padding-col:first-child {
  width: 2rem;
  border-top-left-radius: 1rem;
}

.padding-col:last-child {
  width: 2rem;
  border-top-right-radius: 1rem;
}

th {
  font-family: "Montserrat";
  font-size: 1.4rem;
  color: #e5e5f1;
  font-weight: unset;
  padding: 0.8rem 0 0.8rem 0;
  background-color: #5d5d81;
}

.product-col {
  width: 35rem;
}

.price-col {
  width: 8rem;
}

.times-col {
  width: 2rem;
}

.quantity-col {
  width: 6rem;
}

thead {
  text-align: left;
}

.action-button:hover {
  cursor: pointer;
  background-color: #525271;
}

.action-button:active {
  background-color: #454566;
}
.delete{
  margin-left: 50px;
}
td:first-child {
  border-left: solid #5d5d81 1rem;
}

td:last-child {
  border-right: solid #5d5d81 0.2rem;
}

tbody > tr:last-child > td {
  border-bottom: solid #5d5d81 0.2rem;
}

tbody > tr:last-child > td:first-child {
  border-bottom-left-radius: 1rem;
}

tbody > tr:last-child > td:last-child {
  border-bottom-right-radius: 1rem;
}

tbody > tr:not(:last-child) > td:not(:first-child, :last-child) {
  border-bottom: 0.2rem solid #bfcde0;
}

.empty-prompt {
  text-align: center;
  padding: 1.2rem;
  font-family: "Montserrat";
  font-size: 1.5rem;
  color: #5d5d81;
}
.detail {
  font-family: "Lato";
  font-size: 1.5rem;
  height: 8rem;
}

.product-title {
  display: -webkit-box;
  /* max-width: 200px; */
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.modifiers {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 1.2rem;
}

input[type="text"] {
  width: 4rem;
  height: 1.8rem;
  padding: 0 0.7rem 0 0.7rem;
  border: solid 0.2rem #5d5d81;
  border-radius: 5rem;
  font-family: "Lato";
  font-size: 1.2rem;
  text-overflow: ellipsis;
}

input[type="text"]:focus {
  outline: none;
}

input[type="text"]:disabled {
  border-color: #888888;
}

.button {
  width: 10rem;
  height: 3rem;
  border: none;
  border-radius: 5rem;
  font-family: "Montserrat";
  font-size: large;
  transition: all ease-in-out 100ms;
}

.modify {
  background-color: #5d5d81;
  color: #e5e5f1;
}

.modify:hover {
  cursor: pointer;
  background-color: #525271;
}

.modify:active {
  background-color: #454566;
}

.remove {
  background-color: #fe4874;
  color: #fefcfd;
}

.remove:hover {
  cursor: pointer;
  background-color: #eb3c66;
}

.remove:active {
  background-color: #d92b55;
}

.button:disabled {
  cursor: default;
  background-color: #888888;
}

.disabled {
  color: #888888;
}

.error {
  margin: 0;
  font-size: 1rem;
  padding-top: 0.3rem;
  font-family: "Lato";
  color: #ff0000;
}
select {
  width: 10.5rem;
  height: 2.4rem;
  background-color: #5d5d81;
  border-radius: 5rem;
  transition: all ease-in-out 100ms;
  border: none;
  background: #5d5d81;
  font-family: "Montserrat";
  font-size: 1rem;
  color: #e5e5f1;
  text-align-last: center;
  transition: all ease-in-out 100ms;
}

select:hover {
  cursor: pointer;
  background-color: #525271;
}

.select:active,
select:focus {
  background-color: #454566;
  outline: none;
}
</style>
