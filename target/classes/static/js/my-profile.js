const pass = document.querySelector(".profile-description-tab-pass");
const info = document.querySelector(".profile-description-tab-info");

pass.addEventListener("click", ()=>{
  info.classList.remove("tab-active");
  pass.classList.add("tab-active");
  document.querySelectorAll(".profile-description-container").forEach(el=>{
    el.style.display = "none";
  });
  document.querySelector(".profile-description-second-wrapper").style.display = 'none';
  document.querySelector(".profile-description-pass").style.display = 'block'
});

info.addEventListener("click", ()=>{
  pass.classList.remove("tab-active");
  info.classList.add("tab-active");
  document.querySelectorAll(".profile-description-container").forEach(el=>{
    el.style.display = "block";
  });
  document.querySelector(".profile-description-second-wrapper").style.display = 'flex';
  document.querySelector(".profile-description-pass").style.display = 'none'
});

$('#gender-select').ddslick({
  width: 135,
  selectText: "Select your gender",
});

document.getElementById("gender-select").addEventListener("change", function() {
  var select = document.getElementById("gender-select");
  var selectedGender = select.options[select.selectedIndex].text;
  document.getElementById("selected-gender").innerHTML = selectedGender;
});


function showSelectedDate() {
  var selectedDate = document.getElementById("birthday-picker").value;
  document.getElementById("selected-date").innerHTML = selectedDate;
}

showSelectedDate(); // sayfa yüklendiğinde fonksiyon otomatik olarak çağırılır

document.querySelector(".dd-select").addEventListener("click", ()=>{
  document.querySelector(".dd-selected").style.borderRadius = '10px 10px 0 0'
});

document.querySelectorAll(".dd-option").forEach(e=>{
  e.addEventListener("click", ()=>{
    document.querySelector(".dd-selected").style.borderRadius = '10px'
  });
});