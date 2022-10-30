<template>
  <div class="review">
    <div class="reviewer-rating">
      <p>{{ review.rate }}</p>
    </div>
    <p class="comment">
      {{ displayedComment }}
      <span class="more" v-if="hasMore" v-on:click="expand">more</span>
      <span class="more" v-if="hasLess" v-on:click="collapse">collapse</span>
    </p>
    <div class="hline"></div>
  </div>
</template>

<script>
export default {
  name: "Review",
  props: {
    review: Object,
  },
  data() {
    return {
      displayedComment: "",
      hasMore: false,
      hasLess: false,
    };
  },
  methods: {
    expand() {
      this.displayedComment = this.review.content;
      this.hasMore = false;
      this.hasLess = true;
    },
    collapse() {
      let words = this.review.content.split(" ");
      this.displayedComment = words.slice(0, 200).join(" ") + " ...";
      this.hasMore = true;
      this.hasLess = false;
    },
  },
  created() {
    let words = this.review.content.split(" ");
    if (words.length > 200) {
      this.displayedComment = words.slice(0, 200).join(" ") + " ...";
      this.hasMore = true;
    } else {
      this.displayedComment = this.review.content;
    }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
p {
  padding: 0;
  margin: 0;
}

.review {
  width: 40rem;
  font-family: "Lato";
  margin: 3rem 0;
}

.reviewer-rating {
  display: flex;
  justify-content: space-between;
  font-size: 1.8rem;
  font-style: italic;
}

.comment {
  text-align: justify;
  padding-top: 2rem;
  font-size: 1.5rem;
}

.more {
  text-decoration: underline;
  color: #5d5d81;
}

.more:hover {
  cursor: pointer;
}

.hline {
  width: 100%;
  height: 0.1rem;
  background-color: #bfcde0;
  border-radius: 5rem;
  margin: 2.5rem 0;
}
</style>
