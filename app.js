const express = require("express");
const axios = require("axios");
const app = express();

//looks for folder containing html and css files
app.use(express.static("./css"))

//look into folder and find the html that we dictate
app.get('/', function (req, res) {
    res.sendFile(__dirname + '/css/webpage.html');
    console.log("Loaded the html")
  })
   
//app.listen creates a port at 8000
app.listen(8888, (err)=>{
    if(err)return console.log(err);
    console.log("Listening on http://localhost:8888");
})