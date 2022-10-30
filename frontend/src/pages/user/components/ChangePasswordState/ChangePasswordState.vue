<template>
  <div class="change-password-state">
    <div class="section">
      <p class="label">Current password</p>
      <input type="password" class="content input" v-model="currentPassword" />
      <p class="error" v-if="errors.current !== ''">
        {{ errors.current }}
      </p>
    </div>
    <div class="section">
      <p class="label">New password</p>
      <input type="password" class="content input" v-model="newPassword" />
      <p class="error" v-if="errors.new !== ''">
        {{ errors.new }}
      </p>
    </div>
    <div class="section">
      <button
        class="submit"
        :disabled="submitSucceeded || submitFailed"
        :class="{
          'submit-success': submitSucceeded,
          'submit-failure': submitFailed,
          'submit-normal': !submitSucceeded && !submitFailed,
        }"
        @click="validate"
      >
        {{ updateButtonPrompt }}
      </button>
    </div>
  </div>
</template>

<script>
import md5 from "md5";
import axios from "axios";
export default {
  name: "ChangePasswordState",
  data() {
    return {
      currentPassword: "",
      newPassword: "",
      submitSucceeded: false,
      submitFailed: false,
      updateButtonPrompt: "Update",
      errors: {
        current: "",
        new: "",
      },
    };
  },
  methods: {
    validate() {
      this.errors.current = "";
      this.errors.new = "";
      if (this.currentPassword === "") {
        this.errors.current = "Cannot be empty";
      }
      if (this.newPassword === "") {
        this.errors.new = "Cannot be empty";
      }
      if (this.errors.current === "" && this.errors.new === "") {
        this.submitUpdate();
      }
    },
    submitUpdate() {
      const data = {
        oldPassword: md5(this.currentPassword),
        newPassword: md5(this.newPassword),
      };
      axios
        .post("/api/user/chpasswd", data)
        .then(async (response) => {
          if (response.data.success) {
            this.updateButtonPrompt = "Updated";
            this.submitSucceeded = true;
            await new Promise((resolve) => setTimeout(resolve, 2000));
            this.submitSucceeded = false;
            this.updateButtonPrompt = "Update";
            // Clear form
            this.currentPassword = "";
            this.newPassword = "";
          } else {
            // Populate errors from server
            this.errors.current = response.data.message;
            this.updateButtonPrompt = "Failed";
            this.submitFailed = true;
            await new Promise((resolve) => setTimeout(resolve, 2000));
            this.submitFailed = false;
            this.updateButtonPrompt = "Update";
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
p {
  padding: 0;
  margin: 0;
}

.change-password-state {
  margin-top: 5rem;
  margin-left: 7rem;
}

.section {
  margin-top: 1.5rem;
  width: 25rem;
  height: 5rem;
}

.label {
  font-family: "Montserrat";
  font-size: 1.5rem;
  color: #5d5d81;
}

.content {
  font-family: "Lato";
  font-size: 1.3rem;
  margin-top: 0.5rem;
  color: #000000;
}

.input {
  width: 20rem;
  height: 2.4rem;
  border-radius: 5rem;
  padding-left: 1rem;
  padding-right: 0.8rem;
  box-sizing: border-box;
  border: 0.2rem solid #bfcde0;
  font-size: 1.1rem;
  /*text-overflow: hidden;*/
  line-height: 2rem;
}
.input:focus {
  outline: none;
}

.error {
  font-family: "lato";
  font-size: 1rem;
  color: #ff0000;
}

.submit {
  width: 12rem;
  height: 3rem;
  border: none;
  border-radius: 5rem;
  font-family: "Montserrat";
  font-size: 1.5rem;
  transition: all ease-in-out 100ms;
  background-color: #5d5d81;
  color: #e5e5f1;
  margin-top: 2rem;
}

.submit-normal:hover {
  cursor: pointer;
  background-color: #525271;
}

.submit-normal:active {
  background-color: #454566;
}

.submit-success:disabled {
  cursor: default;
  background-color: green;
}

.submit-failure:disabled {
  cursor: default;
  background-color: #fe4874;
}
</style>
