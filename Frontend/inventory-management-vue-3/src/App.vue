<template>
<v-app>
  

  <v-app-bar app>
  </v-app-bar>

  
  <v-navigation-drawer>
    <v-list></v-list>
  </v-navigation-drawer>
  <!-- Sizes your content based upon application components -->
  <v-main>
 
    <!-- Provides the application the proper gutter -->
    <v-container fluid>
    @{{ user.username }} - {{ fullName }}
    <strong>Followers: </strong> {{ followers}}
     <v-btn block @click="followUser">Follow</v-btn>
     <v-btn block @click="getCenas">Get Cenas</v-btn>
    <div v-for="cena in cenas" :key="cena.id">
      <h3>{{cena.id}}. {{cena.title}}</h3>
    </div>
      <!-- If using vue-router -->

    </v-container>
  </v-main>

  
</v-app>
</template>

<script>
import axios from "axios";


export default {
  name: 'App',
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
