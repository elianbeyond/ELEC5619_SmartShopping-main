<template>
  <div class="filters">
    <category-filter />
    <input
      type="button"
      value="Filter"
      v-on:click="this.$emit('search-and-filter')"
    />
  </div>
</template>

<script>
import CategoryFilter from "./Filters/CategoryFilter.vue";
export default {
  name: "Filters",
  components: {
    CategoryFilter,
  },
};
</script>

<style scoped>
.filters {
  display: flex;
  align-items: center;
  gap: 1.3rem;
}

input[type="button"] {
  width: 6.5rem;
  height: 2.4rem;
  border: none;
  border-radius: 5rem;
  background-color: #5d5d81;
  font-family: "Montserrat";
  font-size: 1rem;
  text-align: center;
  color: #e5e5f1;
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
