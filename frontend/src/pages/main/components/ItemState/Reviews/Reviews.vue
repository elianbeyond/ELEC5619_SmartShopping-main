<template>
  <div>
    <p class="title">Review</p>
    <div class="reviews">
      <review v-for="review in this.reviews" :key="review" :review="review" />
      <button v-if="showNext" v-on:click="loadNextReviews">
        See next three
      </button>
      <p v-if="!showNext" class="no-more-comments">All comments listed</p>
    </div>
  </div>
</template>

<script>
import Review from "./Review.vue";
import axios from "axios";
export default {
  name: "Reviews",
  components: {
    Review,
  },
  data() {
    return {
      productID: "",
      page: 1,
      reviews: [],
      showNext: true,
    };
  },
  methods: {
    loadNextReviews() {
      let uri = "/comment/getCommentList/" + this.productID + "/" + this.page + "/3"
      axios.get(uri).then((response) => {
        if (response.data.code===1) {
          if (response.data.data.length === 0) {
            this.showNext = false;
          } else {
            this.page++;
          }
          this.reviews = this.reviews.concat(response.data.data);
        } else {
          console.log(response.data.message);
        }
      });
    },
  },
  created() {
    this.productID = this.$route.params.itemID;
    this.loadNextReviews();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
p {
  padding: 0;
  margin: 0;
}

.title {
  font-family: "Montserrat";
  font-size: 3.5rem;
  color: #5d5d81;
  text-align: center;
}

.reviews {
  text-align: center;
}

button {
  width: 15rem;
  height: 3rem;
  border: none;
  border-radius: 5rem;
  background-color: #5d5d81;
  font-family: "Montserrat";
  font-size: 1.3rem;
  color: #e5e5f1;
  transition: all ease-in-out 100ms;
  margin-bottom: 10rem;
}

button:hover {
  background-color: #525271;
  cursor: pointer;
}

button:active {
  background-color: #454566;
}

.no-more-comments {
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: center;
  margin-top: 2.5rem;
  margin-bottom: 10rem;
  font-family: "Montserrat";
  font-size: 1.5rem;
  color: #5d5d81;
  font-weight: bold;
}
</style>
