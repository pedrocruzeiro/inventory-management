<template>
  <v-card >
    
    <v-data-table 
    :headers="headers"
    :search="search"
    :items="products"
    sort-by="name"
    :loading="myloadingvariable"
    loading-text="Loading... Please wait"
    class="elevation-1"
  >
    <template v-slot:top >
      <v-toolbar
        flat
      >
        <v-toolbar-title>Products</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-card-title>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
        <v-spacer></v-spacer>
        <v-dialog
          v-model="dialog"
          max-width="500px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              dark
              class="mb-2"
              v-bind="attrs"
              v-on="on"
            >
              New Product
            </v-btn>
          </template>
          <v-card>
            
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.name"
                      label="Name"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.description"
                      label="Description"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.manufacturer"
                      label="Manufacturer"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.productId"
                      label="Product Id"
                    ></v-text-field>
                  </v-col>
                   <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.barcode"
                      label="Barcode"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.price"
                      label="Price"
                      prefix="€"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.stock"
                      label="Stock"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                  <v-combobox 
                    v-model="editedItem.status"
                    label="Status"
                    :items="status"
                    >
                  </v-combobox>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="blue darken-1"
                text
                @click="close"
              >
                Cancel
              </v-btn>
              <v-btn
                color="blue darken-1"
                text
                @click="save"
              >
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="headline">Are you sure you want to delete this product?</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:[`item.actions`]="{ item }">
      <v-icon
        small
        class="mr-2"
        @click="editItem(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
        small
        @click="deleteItem(item)"
      >
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:no-data>
       No data
    </template>
  </v-data-table>
  
  </v-card>
  
</template> 


<script>
import axios from "axios";

  export default {
    name: 'HomeComponent',

    data: () => ({
      myloadingvariable: false,
      dialog: false,
      dialogDelete: false,
      status: [
          'AVAILABLE', 
          'UNAVAILABLE', 
          'DISCONTINUED', 
          'ORDERED',
        ],
      search: '',
        headers: [
          {
            text: 'Product Id',
            align: 'start',
            filterable: false,
            value: 'id',
          },
          { text: 'Name', value: 'name' },
          { text: 'Description', value: 'description' },
          { text: 'Manufacturer', value: 'manufacturer' },
          { text: 'Barcode', value: 'barcode'},
          { text: 'Price', value: 'price'},
          { text: 'Stock', value: 'stock'},
          { text: 'Status', value: 'status'},
          { text: 'Actions', value: 'actions', sortable: false },
        ],
      products: [],
      editedIndex: -1,
      editedItem: {
      name: '',
      description: '',
      manufacturer: '',
      productId: '',
      String: '',
      price: 0.0,
      stock: 0.0,
      status: '',
      },
      defaultItem: {
      },
    
    }),

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'New Product' : 'Edit Product'
      },
    },
     watch: {
      dialog (val) {
        val || this.close()
      },
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },
    methods: {
      addProduct(item){
        console.log(item)
        axios.post("http://localhost:8081/v1/products",
      {
        "name": item.name,
        "description": item.description,
        "manufacturer": item.manufacturer,
        "barcode": item.barcode,
        "price": item.price,
        "stock": item.stock,
        "status": item.status
}
      )
      .then((response) => {
        console.log(response.data)
        this.posts = response.data
        this.myloadingvariable = false
      })
      .catch((error) => {
        console.error(error)
      })
      
    },
    getProducts(){
      axios.get("http://localhost:8081/v1/products")
      .then((response) => {
        console.log(response.data)
        this.products = response.data.products
        this.myloadingvariable = false
      })
      .catch((error) => {
        console.error(error)
      })
    },
    editItem (item) {
        this.editedIndex = this.posts.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },

      deleteItem (item) {
        this.editedIndex = this.posts.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialogDelete = true
      },

      deleteItemConfirm () {
        this.posts.splice(this.editedIndex, 1)
        this.closeDelete()
      },

      close () {
        this.dialog = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },

      closeDelete () {
        this.dialogDelete = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },

      save () {
        console.log(this.editedIndex)
        if (this.editedIndex > -1) {
          Object.assign(this.posts[this.editedIndex], this.editedItem)
        } else {
          this.addProduct(this.editedItem)
          this.products.push(this.editedItem)
        }
        this.close()
      },
  },
  mounted() {
    this.myloadingvariable = true
    //this.getPosts()
    this.getProducts()
    
  }
    }
</script>
