<template>
  <div class="buttons">
    <button class="button nav-button" v-on:click="back">Back</button>
    <button class="button action-button" v-on:click="purchase">Purchase</button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Buttons",
  methods: {
    back() {
      window.location.href = localStorage.getItem("last-visited");
    },

  },
};
</script>

<style scoped>
.buttons {
  display: flex;
  gap: 1.5rem;
  justify-content: left;
  width: 75rem;
}

.button {
  width: 9rem;
  height: 2.4rem;
  border: none;
  border-radius: 5rem;
  font-family: "Montserrat";
  font-size: 1rem;
  transition: all ease-in-out 100ms;
}

.nav-button {
  background-color: #bfcde0;
  color: #000000;
}

.action-button {
  background-color: #5d5d81;
  color: #e5e5f1;
}

.nav-button:hover {
  cursor: pointer;
  background-color: #9baabd;
}

.nav-button:active {
  background-color: #8596ae;
}

.action-button:hover {
  cursor: pointer;
  background-color: #525271;
}

.action-button:active {
  background-color: #454566;
}
</style>
