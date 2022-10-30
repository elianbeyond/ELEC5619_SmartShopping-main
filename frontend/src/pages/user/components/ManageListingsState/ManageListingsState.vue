<template>
  <div class="manage-listings-state">
    <div class="wrapper">
      <listings />
    </div>
  </div>
</template>

<script>
import Listings from "./Listings/Listings.vue";
export default {
  name: "ManageListingsState",
  components: {
    Listings,
  },
};
</script>

<style scoped>
.manage-listings-state {
  width: 100%;
  margin-top: 5rem;
  display: flex;
  flex-flow: column;
  align-items: center;
  justify-content: center;
}

.wrapper {
  display: flex;
  flex-flow: column;
  gap: 2rem;
  margin-bottom: 5rem;
}
</style>
