<template>
  <div class="profile-state">
    <div class="section">
      <p class="label">Name: {{theUsername}}</p>
      <input type="text" class="content input" v-model="Username" />
      <p class="error" v-if="errors.username !== ''">
        {{ errors.username }}
      </p>
    </div>
    <div class="section">
      <p class="label">Password</p>
      <input type="text" class="content input" v-model="Password" />
      <p class="error" v-if="errors.password !== ''">
        {{ errors.password }}
      </p>
    </div>
    <div class="section">
      <p class="label">Email: {{theEmail}}</p>
      <input type="text" class="content input" v-model="Email" />
      <p class="error" v-if="errors.email !== ''">
        {{ errors.email }}
      </p>
    </div>
    <div class="section">
      <p class="label">Address: {{theAddress}}</p>
      <input type="text" class="content input" v-model="Address" />
      <p class="error" v-if="errors.address !== ''">
        {{ errors.address }}
      </p>
    </div>
    <div class="section">
      <button class="update-button" id="submit" v-on:click="validate">
        Update
      </button>
      <p class="error" v-if="errors.password !== ''">
        {{ errors.password }}
      </p>
    </div>
    <!-- <Modal v-model="showModal">
      <password-modal @close="showModal = false" @confirm="submitUpdate" />
    </Modal> -->
  </div>
</template>

<script>
// import PasswordModal from "./PasswordModal.vue";
import axios from "axios";

export default {
  name: "ProfileState",
  // components: {
  //   PasswordModal,
  // },
  data() {
    return {
      showModal: false,
      Username: "",
      Password: "",
      Email: "",
      Address:"",
      theUsername :"",
      theEmail :"",
      theAddress:"",
      errors: {
        username: "",
        address: "",
        email: "",
        password: "",
      },
    };
  },
  mounted() {
            this.getData()
        },
  
  methods: {
    getData() {
    axios.get('/api/user/editprofile').then(
                res => {
                    this.theUsername = res.data.data.username,
                    this.theEmail = res.data.data.userEmail,
                    this.theAddress = res.data.data.address
                }
            )
  },
    validate() {
      const EMAIL_REGEX =
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      let isValid = true;
      this.errors.username = "";
      this.errors.lastName = "";
      this.errors.email = "";
      this.errors.password = "";

      if (this.Username === "") {
        this.errors.username = "Cannot be empty";
        isValid = false;
      }
      if (this.Address === "") {
        this.errors.address = "Cannot be empty";
        isValid = false;
      }
      if (this.Email === "") {
        this.errors.email = "Cannot be empty";
        isValid = false;
      } 
      if(this.Password === ""){
        this.error.password = "Cannot be empty";
        isValid = false;
      }else if (!EMAIL_REGEX.test(this.Email)) {
        this.errors.email = "Invalid email format";
        isValid = false;
      }
      if(isValid){
        axios.post('/api/user/updateinfo',{
                
                //id: this.id,
                username: this.Username,
                userEmail: this.Email,
                password: this.Password,
                address: this.Address
            }).then(
                res => {
                    console.log(res)

                        alert(res.data.msg),
                        this.theUsername = res.data.data.username,
                        this.theEmail = res.data.data.userEmail,
                        this.theAddress = res.data.data.address
                        // this.getData();
                        this.$store.commit('updateName', res.data.data.username)
                }
                
            )
      }
      
    },

  },
};
</script>

<style scoped>
p {
  padding: 0;
  margin: 0;
}

.profile-state {
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

.input-password {
  margin-top: 1rem;
  height: 2.5rem;
  border-radius: 0.5rem;
}

.update-buttons {
  width: 25rem;
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
}

.update-button {
  width: 12rem;
  height: 3rem;
  font-family: "Montserrat";
  font-size: 1.5rem;
  border: none;
  border-radius: 5rem;
  color: #e5e5f1;
  background-color: #5d5d81;
  transition: all ease-in-out 100ms;
  margin-top: 2rem;
}
.update-button:hover {
  background-color: #525271;
  cursor: pointer;
}
.update-button:active {
  background-color: #454566;
}

.error {
  font-family: "lato";
  font-size: 13.5px;
  color: #ff0000;
}

.success {
  background-color: green;
}

.failure {
  background-color: #fe4874;
}
</style>
