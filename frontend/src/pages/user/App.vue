<template>
  <div class="app">
    <top-bar class="top-bar" />
    <div class="nav-state">
      <nav-bar class="nav-bar" />
      <div class="state">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import TopBar from "./components/TopBar.vue";
import NavBar from "./components/NavBar.vue";
export default {
  name: "User",
  components: {
    TopBar,
    NavBar,
  },
};
</script>

<style>
@font-face {
  font-family: "Montserrat";
  src: local("Montserrat"),
    url("../../assets/fonts/Montserrat/Montserrat-Regular.ttf")
      format("truetype");
}

@font-face {
  font-family: "Lato";
  src: local("Lato"),
    url("../../assets/fonts/Lato/Lato-Regular.ttf") format("truetype");
}

html {
  min-width: 120rem;
  font-size: 12px;
  height: 100%;
}

body {
  margin: 0;
  height: 100%;
}

.app,
#app {
  height: 100%;
}

.nav-state {
  display: flex;
  padding-top: 5rem;
  height: calc(100% - 5rem);
}

.top-bar,
.nav-bar {
  /* To make it stay on top */
  position: fixed;
}

.state {
  margin-left: 22rem;
  width: calc(100% - 22rem);
}
</style>
