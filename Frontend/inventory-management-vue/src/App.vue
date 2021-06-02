<template>
  <v-app>
<v-card>
    <v-navigation-drawer permanent app :mini-variant.sync="mini" v-model="drawer">
     <v-list-item class="px-2">
        <v-list-item-avatar>
          <v-icon width="40" height="40">mdi-account-circle</v-icon>
        </v-list-item-avatar>

        <v-list-item-title>John Leider</v-list-item-title>

        <v-btn
          icon
          @click.stop="mini = !mini"
        >
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>
      </v-list-item>

    <v-divider></v-divider>
<!--
      <v-list dense link >
      <v-list-item :to="{ name: 'Home' }">
      <v-list-item-icon>
        <v-icon>home</v-icon>
      </v-list-item-icon>
        <v-list-item-content>
              <v-list-item-title>Home</v-list-item-title>       
        </v-list-item-content>
      </v-list-item>
      <v-list-item :to="{ name: 'About' }">
         <v-list-item-icon>
        <v-icon >info</v-icon>
      </v-list-item-icon>
        <v-list-item-content>
              <v-list-item-title >About</v-list-item-title>       
        </v-list-item-content>
      </v-list-item>
      </v-list>-->
       <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          link
          :to="{ name: item.to }"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
     </v-navigation-drawer>
</v-card>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="./assets/clipart675670.png"
          transition="scale-transition"
          width="40"
        />

        Inventory Management
      </div>

      <v-spacer></v-spacer>

      <v-btn
        href="https://github.com/pedrocruzeiro?tab=repositories"
        target="_blank"
        text
      >
        <span class="mr-2">Pedro Cruzeiro Developer Github</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>
     
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    icon: 'home-icon-silhouette',
    drawer: true,
    mini: true,
    items: [
          { title: 'Home', icon: 'mdi-home-city',to:"Home" },
          { title: 'About', icon: 'mdi-information ', to:"About" },
        ],
    //
  }),
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons");
/* Global CSS */
@import url("https://fonts.googleapis.com/css?family=Montserrat");  
#app {
  font-family: "Montserrat", sans-serif;
  font-weight: 300;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  display: flex;
  flex-direction: column;
}
</style>
