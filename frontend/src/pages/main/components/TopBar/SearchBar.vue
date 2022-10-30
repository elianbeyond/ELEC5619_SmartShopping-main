<template>
  <div class="search-bar">
    <input type="text" v-model="keywords" />
    <input
      type="button"
      value="Search"
      v-on:click="this.$emit('search-and-filter')"
    />
  </div>
</template>

<script>
export default {
  name: "SearchBar",
  computed: {
    keywords: {
      get() {
        return this.$store.state.keywords;
      },
      set(kw) {
        this.$store.commit("updateKeywords", kw);
      },
    },
  },
};
</script>

<style scoped>
.search-bar {
  display: flex;
  align-items: center;
  width: 24rem;
  background-color: #3b3355;
}

input[type="text"] {
  width: 18rem;
  height: 2.4rem;
  border-radius: 5rem 0 0 5rem;
  padding-left: 1rem;
  padding-right: 0.8rem;
  box-sizing: border-box;
  border: none;
  font-family: "Lato";
  font-size: 1.1rem;
  text-overflow: ellipsis;
  line-height: 2rem;
}

input[type="text"]:focus {
  outline: none;
}

input[type="button"] {
  width: 7rem;
  height: 2.4rem;
  border: none;
  border-radius: 0 5rem 5rem 0;
  background-color: #5d5d81;
  font-family: "Montserrat";
  font-size: 1rem;
  color: #fefcfd;
  transition: all ease-in-out 100ms;
}

input[type="button"]:hover {
  cursor: pointer;
  background-color: #525271;
}

input[type="button"]:active {
  background-color: #454566;
}
</style>
