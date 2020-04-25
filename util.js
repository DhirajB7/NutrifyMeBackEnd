var axios = require('axios')

async function getCal(foodName){
    var config = {
        method: 'post',
        url: "https://trackapi.nutritionix.com/v2/natural/nutrients",
        headers: { 'User-Agent': 'Console app' , 'x-app-id':'c1be1f98','x-app-key':'d8bb0849c4ae4eda4d8e061897d1dc1d'},
        data: {'query':foodName.toString()}
    }
    
    var res =  await axios(config)
    var data = res.data.foods[0].nf_calories
    console.log(data)
}

getCal("apple")