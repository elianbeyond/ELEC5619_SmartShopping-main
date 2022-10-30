<template>
  <div class="item-state">
    <item-details class="product-details" :productID="productID" />
    <div class="hline"></div>
    <div class="review-section">
      <reviews />
      <submit-review />
    </div>
  </div>
</template>

<script>
import ItemDetails from "./ItemDetails.vue";
import Reviews from "./Reviews/Reviews.vue";
import SubmitReview from "./Reviews/SubmitReview.vue";
export default {
  name: "ItemState",
  components: {
    ItemDetails,
    Reviews,
    SubmitReview,
  },
  data() {
    return {
      productID: "",
    };
  },
  mounted() {
    this.productID = this.$route.params.itemID;
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.item-state {
  padding-top: 6rem;
  display: flex;
  flex-flow: column;
  align-items: center;
}

.hline {
  width: 80%;
  height: 0.3rem;
  background-color: #bfcde0;
  border-radius: 5rem;
  margin: 2.5rem 0;
}

.review-section {
  display: flex;
  width: 80%;
  justify-content: center;
  gap: 15rem;
}
</style>
