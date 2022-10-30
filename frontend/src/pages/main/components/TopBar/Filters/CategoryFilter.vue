<template>
  <select id="select" v-model="category">
    <option value="">All categories</option>
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
</template>

<script>
export default {
  name: "CategoryFilter",
  computed: {
    category: {
      get() {
        return this.$store.state.category;
      },
      set(category) {
        this.$store.commit("updateCategory", category);
        document.getElementById("select").blur();
      },
    },
  },
};
</script>

<style scoped>
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
