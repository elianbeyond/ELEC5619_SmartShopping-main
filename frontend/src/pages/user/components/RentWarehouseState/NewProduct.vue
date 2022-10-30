<template>
  <div class="new-product">
    <div class="product-details">
      <div>
        <p class="label">Title</p>
        <input type="text" class="input" v-model="title" />
        <p class="error" v-show="errors.title !== ''">{{ errors.title }}</p>
      </div>
      <div>
        <p class="label">Category</p>
        <select class="input" v-model="category">
          <option value="Food">Food</option>
          <option value="Books">Books</option>
          <option value="Clothing">Clothing</option>
          <option value="Computers">Computers</option>
          <option value="Cars">Cars</option>
          <option value="Electronics">Electronics</option>
          <option value="Phones">Phones</option>
          <option value="Home">Home</option>
          <option value="Other">Other</option>
        </select>
        <p class="error" v-show="errors.category !== ''">
          {{ errors.category }}
        </p>
      </div>
      <div>
        <p class="label">Price</p>
        <div class="price-wrapper">
          <p class="price-prefix">$</p>
          <input type="text" class="input price" v-model="price" />
        </div>
        <p class="error" v-show="errors.price !== ''">{{ errors.price }}</p>
      </div>
      <div>
        <p class="label">Stock</p>
        <input type="text" class="input" v-model="stock" />
        <p class="error" v-show="errors.stock !== ''">{{ errors.stock }}</p>
      </div>
      <div>
        <p class="label">Image URL</p>
        <input type="text" class="input" v-model="imageURL" />
        <p class="error" v-show="errors.imageURL !== ''">
          {{ errors.imageURL }}
        </p>
      </div>
      <div>
        <p class="label">Warehouse</p>
        <select class="input" @change="onChange" v-model="location" id="select_location">
          <option value="North Sydney">North Sydney</option>
          <option value="Hurstville">Hurstville</option>
          <option value="Parramatta">Parramatta</option>
          <option value="Strathfield">Strathfield</option>
        </select>
      </div>
      <button
        class="submit"
        :disabled="submitSucceeded || submitFailed"
        :class="{
          'submit-success': submitSucceeded,
          'submit-failure': submitFailed,
          'submit-normal': !submitSucceeded && !submitFailed,
        }"
        @click="add"
      >
        {{ addButtonPrompt }}
      </button>
    </div>
    <!-- show location -->
    <GMapMap
      :center="center"
      :zoom="15"
      map-type-id="terrain"
      style="width: 50rem; height: 50rem"
    >
      <GMapCluster>
        <GMapMarker
          :key="index"
          v-for="(m, index) in markers"
          :position="m.position"
          :clickable="false"
          :draggable="false"
          @click="center = m.position"
        />
      </GMapCluster>
    </GMapMap>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "NewProduct",
  data() {
    return {
      title: "",
      category: "",
      price: "",
      stock: "",
      imageURL: "",
      location: "",
      submitSucceeded: false,
      submitFailed: false,
      addButtonPrompt: "Add",
      errors: {
        title: "",
        category: "",
        price: "",
        stock: "",
        imageURL: "",
        location: "",
      },
      center: { lat: -33.831695, lng: 151.207927 },
      markers: [
        {
          position: {
            lat: -33.831695,
            lng: 151.207927,
          },
        },
      ],
    };
  },
  methods: {
    onChange(e) {
      this.location = e.target.value;
      if (this.location == "Hurstville") {
        this.center = { lat: -33.96501, lng: 151.10221 };
      }
      else if (this.location == "North Sydney") {
        this.center = { lat: -33.831695, lng: 151.207927 };
      }
      else if (this.location == "Parramatta") {
        this.center = { lat: -33.814319, lng: 151.000283 };
      }
      else if (this.location == "Strathfield") {
        this.center = { lat: -33.86859, lng: 151.094789 };
      }
    },
    validate() {
      this.errors = {
        title: "",
        category: "",
        price: "",
        stock: "",
        imageURL: "",
      };
      let isValid = true;

      // Emptiness
      for (let field of ["title", "category", "price", "stock", "location"]) {
        if (this[field] === "") {
          this.errors[field] = "Cannot be empty";
          isValid = false;
        }
      }

      if (this.imageURL.length > 255) {
        this.errors.imageURL = "url too long";
        isValid = false;
      }

      // Positive number
      for (let field of ["price", "stock"]) {
        if (isNaN(this[field]) || this[field] <= 0) {
          this.errors[field] = "Must be a positive number";
          isValid = false;
        }
      }

      // Integer
      if (
        !isNaN(this.stock) &&
        parseInt(this.stock) !== parseFloat(this.stock)
      ) {
        this.errors.stock = "Must be a positive integer";
        isValid = false;
      }
      return isValid;
    },
    add() {
      if (this.validate()) {
        if (this.imageURL === "") {
          this.imageURL = "/imgs/" + this.category + ".jpeg";
        }
        axios
          .post("/api/product/addProduct", {
            name: this.title,
            category: this.category,
            price: this.price,
            quantity: this.stock,
            imglocation: this.imageURL,
            location: this.location,
            productId: 0,
          })
          .then(async (response) => {
            if (response.data.code === 1) {
              this.addButtonPrompt = "Added";
              this.submitSucceeded = true;
              await new Promise((resolve) => setTimeout(resolve, 2000));
              this.submitSucceeded = false;
              this.addButtonPrompt = "Add";
              // Clear form
              this.title = "";
              this.category = "";
              this.price = "";
              this.stock = "";
              this.imageURL = "";
            } else {
              // Populate errors from server
              // for (let errorField of Object.keys(response.data.errors)) {
              //   this.errors[errorField] = response.data.errors[errorField];
              // }
              this.addButtonPrompt = "Failed";
              this.submitFailed = true;
              await new Promise((resolve) => setTimeout(resolve, 2000));
              this.submitFailed = false;
              this.addButtonPrompt = "Add";
            }
          });
      }
    },
  },
};
</script>

<style scoped>
p {
  margin: 0;
}

.new-product {
  gap: 2rem;
  width: 100%;
  margin-top: 5rem;
  display: flex;
  align-items: center;
  justify-content: center;
}

.product-details {
  display: flex;
  flex-flow: column;
  width: 20rem;
  gap: 1.5rem;
  border: #5d5d81 0.2rem solid;
  border-radius: 1rem;
  padding: 2rem;
}

.label {
  font-size: 1.5rem;
  font-family: "Montserrat";
  color: #5d5d81;
}

.input {
  width: 20rem;
  height: 2.4rem;
  border-radius: 5rem;
  padding-left: 1rem;
  padding-right: 0.8rem;
  box-sizing: border-box;
  border: 0.2rem solid #bfcde0;
  font-size: 1.1rem;
  text-overflow: ellipsis;
  line-height: 2rem;
}
.input:focus {
  outline: none;
}

.price {
  width: 17.5rem;
  border-radius: 0 5rem 5rem 0;
  border-left: none;
  padding-left: 0;
}

.price-wrapper {
  display: flex;
}

.price-prefix {
  display: flex;
  align-items: center;
  justify-content: center;
  border: 0.2rem solid #bfcde0;
  width: 2.5rem;
  height: 2rem;
  border-radius: 5rem 0 0 5rem;
  border-right: none;
  font-family: "Montserrat";
  font-size: 1.4rem;
}

.error {
  color: #ff0000;
  font-family: "Lato";
}

.submit {
  width: 9rem;
  height: 2.4rem;
  border: none;
  border-radius: 5rem;
  font-family: "Montserrat";
  font-size: 1.2rem;
  transition: all ease-in-out 100ms;
  background-color: #5d5d81;
  color: #e5e5f1;
}

.submit-normal:hover {
  cursor: pointer;
  background-color: #525271;
}

.submit-normal:active {
  background-color: #454566;
}

.submit-success:disabled {
  cursor: default;
  background-color: green;
}

.submit-failure:disabled {
  cursor: default;
  background-color: #fe4874;
}
</style>
