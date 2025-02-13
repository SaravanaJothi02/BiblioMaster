const text="Simplifies Library Operations, Amplifies Learning Experiences.";
const animated_text=document.getElementById("animated-text");
let index=0;
function animatedText(){
   if(index<text.length){
    animated_text.textContent+=text[index];
    index++;
    setTimeout(animatedText,70);
   }
   else{
    animated_text.style.borderRight="none";
   }
}
animatedText();