<template>
  <div className="search-state">
    <p className="title">Results</p>
    <span className="start content">Searching </span>
    <span className="content parameter" v-if="this.title !== ''">
      "{{ this.title }}"
    </span>
    <span className="content" v-else>All products</span>
    <span className="content" v-if="this.category != null"> of category </span>
    <span className="content parameter" v-if="this.category !== null">
      "{{ this.category }}"
    </span>
    <br/>
    <span className="start content">Found</span>
    <span className="content parameter">{{ this.totalCount }} </span>
    <span className="content"> results </span>
    <div class="products" v-for="items in products" :key="items.id">
      <product
          v-for="product in items"
          :key="product.id"
          :product="product"
      />
    </div>

    <div className="more">
      <button v-if="showNext" v-on:click="loadNextProducts">More</button>
    </div>
  </div>
</template>

<script>
import Product from "./Product.vue";
import axios from "axios";

export default {
  name: "SearchState",
  components: {
    Product,
  },
  data() {
    return {
      title: "",
      category: null,
      maxPrice: 0,
      totalCount: 0,
      page: 6,
      count: 0,
      products: [],
      showNext: false,
    };
  },
  methods: {
    loadNextProducts() {
      if (this.category == "") {
        this.category = null;
      }
      let url = "/search/" + this.title + "/"
          + this.category
          + "/" + this.maxPrice
          + "/0/" + this.page;//page->Number of products appearing

      axios.get(url).then((res) => {
        if (res.data.code === 1) {
          // Todo: check if the result is empty
          this.totalCount = res.data.data.count;
          this.count = res.data.data.count;
          this.maxPrice = res.data.data.products[0].price
          this.$store.state.maxPrice = this.maxPrice

          this.count -= res.data.data.products.length;
          if (this.count <= 0) {
            this.showNext = false;
          } else {
            this.page += this.page;
            this.showNext = true;
          }
          let arr =[];
          let row = Math.ceil(res.data.data.products.length/6);
          for (let i = 0; i < row; i++) {
            arr[i] = [];
            let modLast = res.data.data.products.length % 6 === 0 ? 6 : res.data.data.products.length % 6;
            let lastRow = i === (row - 1) ? modLast : 6;
            for (let j = 0; j < lastRow; j++) {
              arr[i][j] = res.data.data.products[6 * i + j];
            }
          }
          this.products = arr;

          console.log(res.data.data.products)
          console.log(this.products)
        } else {
          console.log(res.data.msg);
        }
      });
    },

    load() {
      this.title = this.$store.state.keywords;
      this.category = this.$store.state.category;
      this.loadNextProducts();
    },
  },
  created() {
    this.load();
  },
  watch: {
    $route() {
      if (window.location.hash.includes("search")) {
        this.products = [];
        this.page = 6;
        this.load();
      }
    },
  },
};
</script>

<style scoped>
.products {
  display: flex;
  flex-wrap: wrap;
  gap: 0;
  margin-left: 20%;
}

p {
  margin-top: 2rem;
  margin-bottom: 1rem;
  padding-left: 5.5rem;
  /*align-items: left;*/
}

.more {
  text-align: center;
  align-items: center;
  justify-content: center;
  margin-bottom: 5rem;
}

.title {
  font-family: "Montserrat";
  font-size: 2.5rem;
  color: #5d5d81;
}

.start {
  padding-left: 40%;
  padding-top: 20px;
}

.content {
  font-family: "Lato";
  font-size: 1.5rem;
  color: #000000;
}

.parameter {
  font-style: italic;
}
.search-state{
  margin-top: 100px;
}
button {
  width: 10rem;
  height: 3rem;
  border: none;
  border-radius: 5rem;
  background-color: #5d5d81;
  font-family: "Montserrat";
  font-size: 1.5rem;
  color: #fefcfd;
  transition: all ease-in-out 100ms;
}

button:hover {
  background-color: #525271;
  cursor: pointer;
}

button:active {
  background-color: #454566;
}
</style>
