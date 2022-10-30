<template>
  <div class="details-wrapper">
    <img :src="imgPath" alt="Product image" />
    <div class="details">
      <p class="title">{{ title }}</p>
      <div>
        <p class="minor-detail first-minor-detail">Product is in {{ location }}</p>
        <p class="minor-detail" v-if="isShow">Stock: {{ stock }}</p>
        <p class="minor-detail">Category: {{ category }}</p>
      </div>
      <p class="price">${{ price }}</p>
      <input type="button" value="Add to cart" v-if="isShow" v-on:click="addToCart" />
      <input type="button" value="Wait for the restock" v-else />

    </div>
    <!-- show location -->
    <GMapMap
      :center="center"
      :zoom="15"
      map-type-id="terrain"
      style="width: 30rem; height: 40rem"
    >
      <GMapCluster>
        <GMapMarker
            :key="index"
            v-for="(m, index) in markers"
            :position="m.position"
            :clickable="false"
            :draggable="false"
            @click="center=m.position"
        />
      </GMapCluster>
    </GMapMap>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "ItemDetails",
  data() {
    return {
      isShow:true,
      productID: "",
      title: "",
      stock: "",
      price: "",
      imgPath: "",
      location: "",
      category: "",
      center: {lat: -33.831695, lng: 151.207927},
      markers: [
        {
          position: {},
        },
      ]
    };
  },
  methods: {
    addToCart() {
      let quantity = prompt("Please specify the quantity to add");
      if (
        quantity !== null &&
        (isNaN(quantity) ||
          parseInt(quantity) !== parseFloat(quantity) ||
          quantity <= 0)
      ) {
        alert("Please enter a positive integer");
      } else if (quantity !== null) {
        quantity = parseInt(quantity);
        if (quantity > this.stock) {
          alert("Not enough stock");
        } else {
          axios.post("/cart/saveCart",{
            productId: this.productID,
            userId: Number(sessionStorage.getItem("userId")),
            quantity: quantity
          }).then(
            async (response) => {
              if (response.data.code === 1) {
                alert(quantity+" Added to cart");
              } else {
                console.log(response.data);
              }
          });
        }
      }
    },
  },
  mounted() {
    this.productID = this.$route.params.itemID;
    axios.get("/product/getProductById/" + this.productID).then((response) => {
      if (response.data.code === 1) {
        let product = response.data.data;
        this.productID = product.id;
        this.title = product.name;
        this.location = product.location;
        this.stock = product.quantity;
        this.price = product.price;
        this.imgPath = product.imglocation;
        this.category = product.category;
        if (this.location == "Hurstville") {
          this.center = {lat: -33.96501, lng: 151.10221};
        }
        else if (this.location == "North Sydney") {
          this.center = {lat: -33.831695, lng: 151.207927};
        }
        else if (this.location == "Parramatta") {
          this.center = {lat: -33.814319, lng: 151.000283};
        }
        else if (this.location == "Strathfield") {
          this.center = {lat: -33.868590, lng: 151.094789};
        }
      } else {
        console.log(response.data);
      }
      if (this.stock<=0){
        this.isShow = false;
      }
    });

    },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
img {
  width: 25rem;
  height: 25rem;
}

p {
  padding: 0;
  margin: 0;
}

.details-wrapper {
  padding-top: 3rem;
  font-family: "Lato";
  display: flex;
}

.details {
  margin-left: 8rem;
}

.title {
  font-size: 2rem;
  width: 60rem;
  height: 10rem;
  font-weight: bold;
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;
}

.minor-detail {
  padding-top: 0.5rem;
  font-size: 1.5rem;
  font-style: italic;
}

.first-minor-detail {
  padding-top: 1.5rem;
}

.price {
  padding-top: 1.5rem;
  font-size: 3rem;
}

input[type="button"] {
  margin-top: 1.5rem;
  width: 15rem;
  height: 4rem;
  font-family: "Montserrat";
  font-size: 1.5rem;
  color: #e5e5f1;
  border-radius: 5rem;
  border: none;
  background-color: #5d5d81;
  transition: all ease-in-out 100ms;
}

input[type="button"]:hover {
  background-color: #525271;
  cursor: pointer;
}

input[type="button"]:active {
  background-color: #454566;
}

.added-to-cart {
  margin-top: 1.2rem;
  font-size: 1.2rem;
  font-family: "Lato";
}
</style>
