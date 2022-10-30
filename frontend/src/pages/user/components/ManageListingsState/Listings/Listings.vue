<template>
  <table>
    <thead class="head">
      <tr>
        <th class="padding-col"></th>
        <th class="product-col">Product</th>
        <th class="category-col">Category</th>
        <th class="price-col">Price</th>
        <th class="stock-col">Stock</th>
        <th class="enabled-col">Location</th>
        <th></th>
        <th class="padding-col"></th>
      </tr>
    </thead>
    <tbody>
      <product
        v-for="product of products"
        :key="product.id"
        :details="product"
        @remove="remove"
      />
      <tr v-if="products.length === 0">
        <td colspan="8" class="empty-prompt">No products. Create one above.</td>
      </tr>
      <tr v-if="!isLastPage">
        <td colspan="8" class="load-more">
          <button class="load-more-button" @click="load">Load more</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import axios from "axios";
import Product from "./Product.vue";
export default {
  name: "Listings",
  components: {
    Product,
  },
  data() {
    return {
      products: [],
      pageSize: 5,
      pageNo: 1,
      isLastPage: true,
    };
  },
  methods: {
    load() {
      axios.get("/api/product/getByUser").then((res) => {
        if (res.data.code === 1) {
          this.products = res.data.data;
        } else {
          console.log(res.data.msg);
        }
      });
    },
    remove(id) {
      axios
        .delete(`/api/product/deleteProduct/${id}`)
        .then((response) => {
          if (response.data.code === 1) {
            this.products = this.products.filter(
              (product) => product.id !== id
            );
          } else {
            console.log(response.data.msg);
          }
        });
    },
  },
  created() {
    this.load();
  },
};
</script>

<style scoped>
table {
  width: 85rem;
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
  width: 30rem;
}

.category-col,
.price-col,
.stock-col {
  width: 10rem;
}

.enabled-col {
  width: 12rem;
}

thead {
  text-align: left;
}

td:first-child {
  border-left: solid #5d5d81 0.2rem;
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

.load-more {
  text-align: center;
  padding: 2rem 0;
}

.load-more-button {
  border-radius: 5rem;
  border: none;
  font-family: "Montserrat";
  width: 10rem;
  height: 2.5rem;
  font-size: 1.2rem;
  background-color: #5d5d81;
  color: #e5e5f1;
  transition: all ease-in-out 100ms;
}

.load-more-button:hover {
  cursor: pointer;
  background-color: #525271;
}

.load-more-button:active {
  background-color: #454566;
}
</style>
