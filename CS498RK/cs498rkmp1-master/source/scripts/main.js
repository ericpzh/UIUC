window.onbeforeunload = function () {
  scrollTo(0,0);
}
window.onload = function(){
  document.getElementById ("navbar-item-home").addEventListener ("click", tohome,false);
  document.getElementById ("navbar-item-about").addEventListener ("click", toabout,false);
  document.getElementById ("navbar-item-gallery").addEventListener ("click", togallery,false);
  document.getElementById ("navbar-item-history").addEventListener ("click", tohistory,false);
  document.getElementById ("prev").addEventListener ("click", prev,false);
  document.getElementById ("next").addEventListener ("click", next,false);
  document.getElementById ("close1").addEventListener ("click", close1,false);
  document.getElementById ("close2").addEventListener ("click", close2,false);
  document.getElementById ("close3").addEventListener ("click", close3,false);
  document.getElementById ("col-1").addEventListener ("click", open1,false);
  document.getElementById ("col-2").addEventListener ("click", open2,false);
  document.getElementById ("col-3").addEventListener ("click", open3,false);
};

function resize(){
    if (window.screen.availWidth <= 1024) {
      document.getElementsByClassName("modal-active")[0].style.width = "85%";
    }else if (window.screen.availWidth <= 1280){
      document.getElementsByClassName("modal-active")[0].style.width = "65%";
    }else if (window.screen.availWidth <= 1366){
      document.getElementsByClassName("modal-active")[0].style.width = "60%";
    }else {
      document.getElementsByClassName("modal-active")[0].style.width = "45%";
    }
}

function close1(){
  var element = document.getElementById ("modal1");
  element.className = element.className.replace(/\bmodal-active\b/g, "modal-inactive");
  document.getElementById ("modal").style.display = "none";
}
function close2(){
  var element = document.getElementById ("modal2");
  element.className = element.className.replace(/\bmodal-active\b/g, "modal-inactive");
  document.getElementById ("modal").style.display = "none";
}
function close3(){
  var element = document.getElementById ("modal3");
  element.className = element.className.replace(/\bmodal-active\b/g, "modal-inactive");
  document.getElementById ("modal").style.display = "none";
}
function open1(){
  var element = document.getElementById ("modal1");
  element.className = element.className.replace(/\bmodal-inactive\b/g, "modal-active");
  document.getElementById ("modal").style.display = "block";
  //resize();
}
function open2(){
  var element = document.getElementById ("modal2");
  element.className = element.className.replace(/\bmodal-inactive\b/g, "modal-active");
  document.getElementById ("modal").style.display = "block";
  //resize();
}
function open3(){
  var element = document.getElementById ("modal3");
  element.className = element.className.replace(/\bmodal-inactive\b/g, "modal-active");
  document.getElementById ("modal").style.display = "block";
  //resize();
}

function tohome(){
  scrollToY(0,2);
}

function toabout(){
  var height = document.getElementById ("video").clientHeight*0.9;
  scrollToY(height,2);
}

function togallery(){
  var height = document.getElementById ("video").clientHeight+document.getElementById ("triple-col-loc").clientHeight*0.9;
  scrollToY(height,2);
}

function tohistory(){
  var height = document.getElementById ("video").clientHeight+document.getElementById ("triple-col-loc").clientHeight+document.getElementById ("carousel-loc").clientHeight*0.75;
  scrollToY(height,2);
}

window.onscroll = function() {
  navbar();
  indicator();
};

function navbar() {
  if (document.body.scrollTop > 40 || document.documentElement.scrollTop > 40) {
    document.getElementById("navbar").style.padding = "20px 10px";
    document.getElementById("navbar-title").style.fontSize = "30px";
    document.getElementById("navbar-item-home").style.fontSize = "25px";
    document.getElementById("navbar-item-about").style.fontSize = "25px";
    document.getElementById("navbar-item-gallery").style.fontSize = "25px";
    document.getElementById("navbar-item-history").style.fontSize = "25px";
    document.getElementById("navbar-title-logo").style.width = "35px";
    document.getElementById("navbar-title-logo").style.height = "35px";
  } else {
    document.getElementById("navbar").style.padding = "40px 10px";
    document.getElementById("navbar-title").style.fontSize = "40px";
    document.getElementById("navbar-item-home").style.fontSize = "30px";
    document.getElementById("navbar-item-about").style.fontSize = "30px";
    document.getElementById("navbar-item-gallery").style.fontSize = "30px";
    document.getElementById("navbar-item-history").style.fontSize = "30px";
    document.getElementById("navbar-title-logo").style.width = "50px";
    document.getElementById("navbar-title-logo").style.height = "50px";
  }
}

function indicator(){
  var height1 = document.getElementById ("video").clientHeight*0.9;
  var height2 = document.getElementById ("video").clientHeight+document.getElementById ("triple-col-loc").clientHeight*0.9;
  var height3 = document.getElementById ("video").clientHeight+document.getElementById ("triple-col-loc").clientHeight+document.getElementById ("carousel-loc").clientHeight*0.75;

  if (((document.body.scrollTop > 0 && document.body.scrollTop < height1) || (document.documentElement.scrollTop > 0 &&  document.documentElement.scrollTop < height1))) {
    var element = document.getElementById("navbar-item-home");
    element.className = element.className.replace(/\binactive\b/g, "active");
    var element = document.getElementById("navbar-item-about");
    element.className = element.className.replace(/\bactive\b/g, "inactive");
    var element = document.getElementById("navbar-item-gallery");
    element.className = element.className.replace(/\bactive\b/g, "inactive");
    var element = document.getElementById("navbar-item-history");
    element.className = element.className.replace(/\bactive\b/g, "inactive");
  } else if((document.body.scrollTop > height1 && document.body.scrollTop < height2) || (document.documentElement.scrollTop > height1 &&  document.documentElement.scrollTop < height2)) {
    var element = document.getElementById("navbar-item-home");
    element.className = element.className.replace(/\bactive\b/g, "inactive");
    var element = document.getElementById("navbar-item-about");
    element.className = element.className.replace(/\binactive\b/g, "active");
    var element = document.getElementById("navbar-item-gallery");
    element.className = element.className.replace(/\bactive\b/g, "inactive");
    var element = document.getElementById("navbar-item-history");
    element.className = element.className.replace(/\bactive\b/g, "inactive");
  } else if((document.body.scrollTop > height2 && document.body.scrollTop < height3) || (document.documentElement.scrollTop > height2 &&  document.documentElement.scrollTop < height3)) {
    var element = document.getElementById("navbar-item-home");
    element.className = element.className.replace(/\bactive\b/g, "inactive");
    var element = document.getElementById("navbar-item-about");
    element.className = element.className.replace(/\bactive\b/g, "inactive");
    var element = document.getElementById("navbar-item-gallery");
    element.className = element.className.replace(/\binactive\b/g, "active");
    var element = document.getElementById("navbar-item-history");
    element.className = element.className.replace(/\bactive\b/g, "inactive");
  } else if ((document.body.scrollTop > height3) || (document.documentElement.scrollTop > height3)) {
    var element = document.getElementById("navbar-item-home");
    element.className = element.className.replace(/\bactive\b/g, "inactive");
    var element = document.getElementById("navbar-item-about");
    element.className = element.className.replace(/\bactive\b/g, "inactive");
    var element = document.getElementById("navbar-item-gallery");
    element.className = element.className.replace(/\bactive\b/g, "inactive");
    var element = document.getElementById("navbar-item-history");
    element.className = element.className.replace(/\binactive\b/g, "active");
  }
}

function prev(){
  var element1 = document.getElementById("carousel-1");
  var element2 = document.getElementById("carousel-2");
  var element3 = document.getElementById("carousel-3");
  if (element2.className == "carousel-active"){
    element1.className = element1.className.replace(/\bcarousel-inactive\b/g, "carousel-active");
    element2.className = element2.className.replace(/\bcarousel-active\b/g, "carousel-inactive");
    element3.className = element3.className.replace(/\bcarousel-inactive\b/g, "carousel-inactive");
  }else if (element3.className == "carousel-active"){
    element1.className = element1.className.replace(/\bcarousel-inactive\b/g, "carousel-inactive");
    element2.className = element2.className.replace(/\bcarousel-inactive\b/g, "carousel-active");
    element3.className = element3.className.replace(/\bcarousel-active\b/g, "carousel-inactive");
  }else{

  }
}

function next(){
  var element1 = document.getElementById("carousel-1");;
  var element2 = document.getElementById("carousel-2");
  var element3 = document.getElementById("carousel-3");
  if (element1.className == "carousel-active"){
    element1.className = element1.className.replace(/\bcarousel-active\b/g, "carousel-inactive");
    element2.className = element2.className.replace(/\bcarousel-inactive\b/g, "carousel-active");
    element3.className = element3.className.replace(/\bcarousel-inactive\b/g, "carousel-inactive");
  }else if (element2.className == "carousel-active"){
    element1.className = element1.className.replace(/\bcarousel-inactive\b/g, "carousel-inactive");
    element2.className = element2.className.replace(/\bcarousel-active\b/g, "carousel-inactive");
    element3.className = element3.className.replace(/\bcarousel-inactive\b/g, "carousel-active");
  }else{

  }
}

function scrollToY(y,flag) {
  if(window.scrollY<y){
    if (flag == 2 || flag == 0) {flag = 0;}
    else {return;}
    setTimeout(function() {
       window.scrollTo(0,window.scrollY+40);
        scrollToY(y,flag);
    }, 10);
   }
  else if(window.scrollY>y){
    if (flag == 2 || flag == 1) {flag = 1;}
    else {return;}
      setTimeout(function() {
         window.scrollTo(0,window.scrollY-40);
          scrollToY(y,flag);
      }, 10);
     }
  }
