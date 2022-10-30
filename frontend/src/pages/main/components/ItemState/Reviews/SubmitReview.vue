<template>
  <div class="section-wrapper">
    <p class="title">Submit a review</p>
    <div v-if="this.$store.state.signedIn">
      <textarea v-model="comment"></textarea>
      <div class="rating-submit">
        <select v-model="rating">
          <option value="" selected disabled>Rating</option>
          <option value="0">0</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
        <button
          v-on:click="submit"
          :disabled="comment.length === 0 || rating === ''"
          id="submit"
        >
          Submit
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "SubmitReview",
  data() {
    return {
      comment: "",
      rating: "",
    };
  },
  methods: {
    submit() {
      document.getElementById("submit").disabled = true;
      axios
        .post("/comment/saveComment", {
          productId: this.$route.params.itemID,
          userId: sessionStorage.getItem("userId"),
          rate: Number(this.rating),
          content: this.comment,
        })
        .then(async (response) => {
          if (response.data.code === 1) {
            this.comment = "";
            this.rating = "";
            document.getElementById("submit").textContent = "Success";
            document.getElementById("submit").classList.add("success");
            await new Promise((resolve) => setTimeout(resolve, 2000));
            document.getElementById("submit").classList.remove("success");
            document.getElementById("submit").textContent = "Submit";
          } else {
            alert(response.data.message);
            document.getElementById("submit").textContent = "Failed";
            document.getElementById("submit").classList.add("failure");
            await new Promise((resolve) => setTimeout(resolve, 2000));
            document.getElementById("submit").classList.remove("failure");
            document.getElementById("submit").textContent = "Submit";
            document.getElementById("submit").disabled = false;
          }
        });
    },
  },
  watch: {
    rating: function () {
      document.activeElement.blur();
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
p {
  padding: 0;
  margin: 0;
}

.section-wrapper {
  width: 45rem;
}

.title {
  font-family: "Montserrat";
  font-size: 3.5rem;
  color: #5d5d81;
}

textarea {
  display: flex;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  margin-top: 3rem;
  padding: 1rem;
  width: 100%;
  height: 12.5rem;
  resize: vertical;
  border-radius: 1.2rem;
  font-family: "Lato";
  font-size: 1.4rem;
  text-align: justify;
}

.rating-submit {
  float: right;
}

select,
button {
  margin-top: 1.2rem;
  margin-left: 1.2rem;
  width: 8rem;
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

select:hover,
button:hover {
  cursor: pointer;
  background-color: #525271;
}

select:focus,
button:active {
  background-color: #454566;
  outline: none;
}

button:disabled {
  background-color: #8f8f8f;
  cursor: default;
}

.success:disabled {
  background-color: green;
}

.failure:disabled {
  background-color: #fe4874;
}

.not-signed-in {
  font-family: "Lato";
  font-size: 2rem;
  padding-top: 2rem;
}
</style>
