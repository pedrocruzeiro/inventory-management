<template>
<v-container>
  <div id="about">

    
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          contain
          max-height="500"
          max-with="500"
          src="../assets/InventoryManagement_Hero@3x.png"
          transition="scale-transition"
          width="40"
        />
      </div>

        <h1>Inventory Management tool V1.0.0</h1>
      </div>
</v-container>
  <!--
    @{{ user.username }} - {{ fullName }}
    <strong>Followers: </strong> {{ followers}}
    <v-btn small elevation="" color="" @click="followUser">Follow</v-btn>
    <v-btn @click="getCenas">get</v-btn>
    <div v-for="cena in cenas" :key="cena.id">
      <h3>{{cena.id}}. {{cena.title}}</h3>
    </div> -->
  
</template>

<script>
import axios from "axios";

export default {
  name: 'about',
  data(){
    return{
    collapseOnScroll: true,
    cenas: [],
    followers: 0,
    user: {
      id: 1,
      username: "pedroc",
      firstName: "Pedro",
      lastName: "Cruzeiro",
      email: "pedrov@cenas.com",
      isAdmin: true
    }
    }
  },
  watch:{
    followers(newFollowerCount, oldFollowerCount ){
      if(oldFollowerCount < newFollowerCount){
        console.log("This user gained a follower.")
      }
    }
  },
  computed: {
    fullName(){
      return `${this.user.firstName} ${this.user.lastName}`;
    }
  },
  methods: {
    followUser(){
      this.followers++;
    },
    getCenas(){
      axios.get("https://jsonplaceholder.typicode.com/posts")
      .then((response) => {
        console.log(response.data)
        this.cenas = response.data
      })
      .catch((error) => {
        console.error(error)
      })
    }
  },
  mounted() {
    this.followUser();
  }

}
</script>


