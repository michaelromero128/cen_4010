//super super fun time wee yay.js
console.log("peepeepoopoo");
var mooseclick=false;
var wooseclick=false;
var aooseclick=false;
var cooseclick=true;
var booseclick=false;
var city1=1;
// $(document).ready(function() {

$('#city1').keypress(function(event){
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13'){
           clickMe(); 
        }
});
$( "#booton" ).on( "click", function( ) {
    clickMe();
  });

$( "#home-tab" ).on( "click", function( ) {
    console.log("home_unlock");
    resetclick();
    mooseclick=false;
    $("#home").removeClass("d-none",60);
    console.log(mooseclick);

});

$( "#weather-tab" ).on( "click", function( ) {
    console.log("weather_unlock");
    if(!wooseclick){resetclick();}
    wooseclick=true;
    if(mooseclick){clickMe();}
    $("#weather-tab").addClass("font-weight-heavy",30);
    console.log(wooseclick);

});
  
$( "#cinfo-tab" ).on( "click", function( ) {
    console.log("cinfo_unlock");
    if(!cooseclick){resetclick();};
    cooseclick=true;
    if(mooseclick){clickMe();}
    console.log(cooseclick);

  });

$( "#bus-tab" ).on( "click", function( ) {
    console.log("bus_unlock");
    if(!booseclick){resetclick();};
    booseclick=true;
    if(mooseclick){clickMe();}
    console.log(booseclick);

  });

$( "#com-tab" ).on( "click", function( ) {
    console.log("com_unlock");
    if(!aooseclick){resetclick();};
    aooseclick=true;
    if(mooseclick){clickMe();}
    console.log(aooseclick);
  });

function resetclick(){

    if(wooseclick){
        console.log("weather_close");
        wooseclick=false;
        $("#forecast").addClass("d-none",60);
    }
    if(cooseclick){
        console.log("cinfo_close");   
        cooseclick=false;
        $("#covids").addClass("d-none",60);
    }
    if(booseclick){
        console.log("bus_close");
        booseclick=false;
        $("#yelp").addClass("d-none",60);
    }
    if(aooseclick){
        console.log("com_close");
        aooseclick=false;
        $("#comment").addClass("d-none",60);
    }
}

function clickMe() {
    console.log("clickMe");
    city1 = $("#city1").val().trim();
    console.log(city1);
    getComments();

    if(!mooseclick){
        mooseclick=true;
        $( "#home" ).addClass( "d-none",60 );
        if((wooseclick && booseclick && cooseclick &&aooseclick)==0){cooseclick=true;}
        }

    if(wooseclick){
        compareBtnClick();
        $( "#forecast" ).removeClass( "d-none",60 );
        return;
    }
    if(booseclick){
        yelp();
        $("#yelp").removeClass("d-none",60);
        return;
    }
    if(cooseclick){
        init();
        $("#covids").removeClass("d-none",60);
        return;
    }
    
    if(aooseclick){
        init();
        $("#comment").removeClass("d-none",60);
        return;
    }

  }

//************************************************ */
//YELP FUNCTIONS
//************************************************* */
  function yelp(){
    console.log("yelpcall");
    city1 = $("#city1").val().trim();
    var CITY = city1;
    var myurl = "http://52.91.156.204:8080/https://api.yelp.com/v3/businesses/search?location=" + CITY;
    $.ajax({
       url: myurl,
       headers: {
        'Authorization':'Bearer eTFFe41PY3fW6sZUph2s6jYZyYqA6Yi2_5d88yYAUPSCts6Y8wEISL4FpaBjWU9ZALdvF53L07MKXEikvor2pzDtNbgwVl4MKVRQhDm5lj9tV2AD6p-mdvMH--SqX3Yx',
    },
       method: 'GET',
       dataType: 'json',
       success: function(data){
           // Grab the results from the API JSON return
           var totalresults = data.total;
           // If our results are greater than 0, continue
           if (totalresults > 0){
               // Display a header on the page with the number of results
               $('#results').append('<h2>We discovered ' + totalresults + ' results!</h2>');
               // Itirate through the JSON array of 'businesses' which was returned by the API
               $.each(data.businesses, function(i, item) {
                   // Store each business's object in a variable
                   var id = item.id;
                   var alias = item.alias;
                   var phone = item.display_phone;
                   var image = item.image_url;
                   var name = item.name;
                   var rating = item.rating;
                   var reviewcount = item.review_count;
                   var address = item.location.address1;
                   var city = item.location.city;
                   var state = item.location.state;
                   var zipcode = item.location.zip_code;
                   var url = item.url;
                   // Append our result into our page
                   $('#results').append('<div id="' + id + '" style="card margin-top:50px;margin-bottom:50px;"><img class="card-img-top" src="' + image + '" alt="Business img" style="width:400px;height:300px;"><div class="card-body"><h5 class="card-title">' + name + '</h5> <br>(' + alias + ')<br>Business ID: ' + id + '<br> Located at: ' + address + ' ' + city + ', ' + state + ' ' + zipcode + '<br>The phone number for this business is: ' + phone + '<br>This business has a rating of ' + rating + ' with ' + reviewcount + ' reviews.<br> Click <a href="'+url+'">HERE</a> to write a review!</div></div>');
                   
             });
           } else {
               // If our results are 0; no businesses were returned by the JSON therefor we display on the page no results were found
               $('#results').append('<h5>We discovered no results!</h5>');
           }
       }
    });      

  }
  
  function getComments(){
	  $("#comments").remove();
	  $("#comment").prepend('<div id = "comments"></div');
	  
	  city1 = $("#city1").val().trim();
	  var CITY = city1
	  var myurl = "http://mockproject128.com/getComment/"+encodeURIComponent(CITY);
	  //var myurl = "http://localhost/getComment/"+encodeURIComponent(CITY);
	    $.ajax({
	       url: myurl,
	       method: 'GET',
	       dataType: 'json',
	       success: function(totalresults){
	           // Grab the results from the API JSON return
	           data = totalresults;
	           // If our results are greater than 0, continue
	           if (data.length > 0){
	               // Display a header on the page with the number of results
	              
	               // Itirate through the JSON array of 'businesses' which was returned by the API
	               $.each(data, function(i, item) {
	                   // Store each business's object in a variable
	                   var id = item.id;
	                   var zaText = item.text;
	                   var zaUserName = item.userName
	                   // Append our result into our page
	                   $('#comments').append('<div>'+zaText+'<br>'+'- '+zaUserName+'</div>');
	                   
	             });
	           } else {
	               // If our results are 0; no businesses were returned by the JSON therefor we display on the page no results were found
	               $('#comments').append('<h5>No comments yet. Make the first!</h5>');
	           }
	       }
	    });      

	  }
  
  function testPost(){
	    console.log("yelpcall");
	    var CITY = "miami";
	    var zeText = {"text":"wuzzle"}
	    var myurl = "http://mockproject128.com/postComment/miami"
	    $.ajax({
	    	type: "POST",
	    	url: myurl,
	    	data: JSON.stringify(zeText),
	       dataType: 'json',
	       contentType: "application/json",
	       success: function(){
	    	   location.reload();
	       }
	    });      
}

function realPost(){
	  city1 = $("#city1").val().trim();
	  var CITY = city1
	  var zeText = {"text": $("#text").val().trim()}
	  var myurl = "http://mockproject128.com/postComment/"+encodeURIComponent(CITY);
	  //var myurl = "http://localhost/postComment/"+encodeURIComponent(CITY);
	  $.ajax({
	    	type: "POST",
	    	url: myurl,
	    	data: JSON.stringify(zeText),
	       dataType: 'json',
	       contentType: "application/json",
	    	   success: function(){
		    	   location.reload();
		       } 
	    }); 
}

//************************************************ */
//COVID_INFO FUNCTIONS
//************************************************* */
  function init(){
    var url = "https://api.covid19api.com/summary"
    
    var dat =''
    $.get(url,function(data){
        console.log(data.Global)
        
        data = `
<td>${data.Global.NewConfirmed}</td>
<td>${data.Global.TotalConfirmed}</td>
<td>${data.Global.NewDeaths}</td>
<td>${data.Global.TotalDeaths}</td>
<td>${data.Global.NewRecovered}</td>
<td>${data.Global.TotalRecovered}</td>

`
$("#data").html(data)
        
    })
}

  
//   function getVal(elem) {
//     var val = parseInt(document.getElementById(elem).value);
//     if(isNaN(val))
//         {
//             mooseclick=true;
//         }
//     if(!isNaN(val))
//     {
//         mooseclick=false;
//     }
//         console.log(val);
//     return val;
//   }

//   function sendResult(min, max, med, ran, mean) {
//     document.getElementById("max").innerHTML = max;
//     document.getElementById("min").innerHTML = min;
//     document.getElementById("med").innerHTML = med;
//     document.getElementById("ran").innerHTML = ran;
//     document.getElementById("mean").innerHTML = mean;

//   }
// function printo(){
//     var yay='Thank you!', nay='Please enter the three required integers in order to achieve a desired result.';
//     console.log(mooseclick);
//     if (mooseclick)
//     {
//         document.getElementById("err").innerHTML = nay;
//         return;
//     }
//     document.getElementById("err").innerHTML = yay;

// }


