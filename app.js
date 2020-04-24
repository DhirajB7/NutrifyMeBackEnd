var express = require("express")
var bodyParser = require("body-parser")
var app = express()  //so many methods so go to variable

app.use(bodyParser.urlencoded({extended:true}))

var port = 5000

////////////////////////////////


var meals = [
    {
        "meal_id": 0,
        "food_name": "string",
        "description": "string",
        "calorie": 0,
        "date": "string",
        "time": "string",
        "is_in_days_limit": true
      },
    {
        "meal_id": 1,
        "food_name": "string",
        "description": "string",
        "calorie": 0,
        "date": "string",
        "time": "string",
        "is_in_days_limit": true
      },
      {
        "meal_id": 2,
        "food_name": "string",
        "description": "string",
        "calorie": 0,
        "date": "string",
        "time": "string",
        "is_in_days_limit": true
      },
      {
        "meal_id": 3,
        "food_name": "string",
        "description": "string",
        "calorie": 0,
        "date": "string",
        "time": "string",
        "is_in_days_limit": true
      }
      ,
      {
        "meal_id": 4,
        "food_name": "string",
        "description": "string",
        "calorie": 0,
        "date": "string",
        "time": "string",
        "is_in_days_limit": true
      }
      ,
      {
        "meal_id": 5,
        "food_name": "string",
        "description": "string",
        "calorie": 0,
        "date": "string",
        "time": "string",
        "is_in_days_limit": true
      } 
]

var users = [
    {
        "id": 0,
        "username": "0",
        "firstName": "string",
        "lastName": "string",
        "email": "string",
        "password": "string",
        "phone": "string",
        "calories_per_day": 0,
        "userStatus": 0
      },
      {
        "id": 1,
        "username": "1",
        "firstName": "string",
        "lastName": "string",
        "email": "string",
        "password": "string",
        "phone": "string",
        "calories_per_day": 0,
        "userStatus": 0
      },
      {
        "id": 2,
        "username": "2",
        "firstName": "string",
        "lastName": "string",
        "email": "string",
        "password": "string",
        "phone": "string",
        "calories_per_day": 0,
        "userStatus": 0
      },
      {
        "id": 3,
        "username": "3",
        "firstName": "string",
        "lastName": "string",
        "email": "string",
        "password": "string",
        "phone": "string",
        "calories_per_day": 0,
        "userStatus": 0
      },
      {
        "id": 4,
        "username": "4",
        "firstName": "string",
        "lastName": "string",
        "email": "string",
        "password": "string",
        "phone": "string",
        "calories_per_day": 0,
        "userStatus": 0
      }
]

//////////////////////////////




app.listen(port,()=>console.log("localhost:"+port))

//Hello
app.get('/',(req,res)=>{
    res.senjsond({message: 'I AM GET HELLO'})
})


//Meal

app.post('/meals',(req,res)=>{

  var jsonMeal =   {
        "meal_id": Math.round(Math.random()*100),
        "food_name": "string",
        "description": "string",
        "calorie": 0,
        "date": "string",
        "time": "string",
        "is_in_days_limit": true
      }

    meals.push(jsonMeal)
    res.send("MEAL ADDED ID : " + jsonMeal.meal_id)
})

app.get('/meals/:id',(req,res)=>{

    var id = Number(req.params.id)
    var idCheck = meals.map(a=>a.meal_id).filter(a=>a===id)
    var mealJson
    if(idCheck>0){
         mealJson = meals.filter((a)=>a.meal_id===id)[0]
    }else{
         mealJson = "MEAL ID NOT FOUND" 
    }
    res.send(mealJson)
   
})

app.put('/meals/:id/:propName/:propValue',(req,res)=>{
     
    var id = Number(req.params.id)
    var proertyName = req.params.propName
    var propertyValue = req.params.propValue 
    var idCheck = meals.map(a=>a.meal_id).filter(a=>a===id)
    var mealJson
    if(idCheck>0){
        mealJson = meals[idCheck]
        mealJson[proertyName] =  propertyValue
        
   }else{
        mealJson = "MEAL ID NOT FOUND" 
   }
   res.send(mealJson)
})

app.delete('/meals/:id',(req,res)=>{

    var id = Number(req.params.id)
    var toBeDeleted = meals.filter(a=>a.meal_id===id)[0]
    var indexOfDeleted = meals.indexOf(toBeDeleted)
    if(indexOfDeleted>=0){
        meals.splice(indexOfDeleted,indexOfDeleted+1)
        res.send("Meal_Id:"+id+" is deleted")
    }else{
        res.send("MEAL ID NOT FOUND")
    }
    
})


//USER

app.post('/user',(req,res)=>{

    var number = Math.round(Math.random()*100)
    var jsonUser =   {
        "id": number,
        "username": number.toString(),
        "firstName": "string",
        "lastName": "string",
        "email": "string",
        "password": "string",
        "phone": "string",
        "calories_per_day": 0,
        "userStatus": 0
      }
      users.push(jsonUser)
      res.send("user ID and USERNAME: " + jsonUser.id +" and "+jsonUser.username)
  })

app.get('/user/:username',(req,res)=>{

   var un = req.params.username
    var idCheck = users.map(a=>a.username).filter(a=>a===un)
    var userJson
    if(idCheck>0){
        userJson = users.filter((a)=>a.username===un)[0]
    }else{
        userJson = "USER NOT FOUND" 
    }
    res.send(userJson)
})

app.put('/user/:username/:propName/:propValue',(req,res)=>{

    var un = req.params.username
    var proertyName = req.params.propName
    var propertyValue = req.params.propValue 
    var idCheck = users.map(a=>a.username).filter(a=>a===un)
    var userJson
    if(idCheck>0){
        userJson = users[idCheck]
        userJson[proertyName] =  propertyValue
   }else{
    userJson = "USER NOT FOUND" 
   }
   res.send(userJson)
})

app.delete('/user/:username',(req,res)=>{

    var un = req.params.username
    var toBeDeleted = users.filter(a=>a.username===un)[0]
    var indexOfDeleted = users.indexOf(toBeDeleted)
    if(indexOfDeleted>=0){
        users.splice(indexOfDeleted,indexOfDeleted+1)
        res.send("username :"+un+" is deleted")
    }else{
        res.send("USER NOT FOUND")
    }
})



//All OTHER PAGES
app.get('*',(req,res)=>{
    res.send("Oh hooooo....404")
})