//Add this script to open the sidebar on page load 
window.addEventListener('load', function() {
  var sidebar = document.getElementById("mySidebar");
  var mainContent = document.getElementById("main");
  var openCloseBtn = document.getElementById("openCloseBtn");
  var sidebarLinks = document.querySelectorAll(".sidebar a");

  // Set the initial state to closed
  sidebar.style.width = "50px";
  mainContent.style.marginLeft = "50px";
  openCloseBtn.innerText = "☰";

  // Hide icon names on page load
  for (var i = 0; i < sidebarLinks.length; i++) {
    sidebarLinks[i].querySelector(".hide-text").style.display = "none";
    sidebarLinks[i].querySelector(".show-text").style.display = "none";
  }

  // Show icon names when opening the sidebar
  function showIconNames() {
    for (var i = 0; i < sidebarLinks.length; i++) {
      sidebarLinks[i].querySelector(".hide-text").style.display = "none";
      sidebarLinks[i].querySelector(".show-text").style.display = "inline";
    }
  }

  // Hide icon names when closing the sidebar
  function hideIconNames() {
    for (var i = 0; i < sidebarLinks.length; i++) {
      sidebarLinks[i].querySelector(".hide-text").style.display = "inline";
      sidebarLinks[i].querySelector(".show-text").style.display = "none";
    }
  }

  // Toggle the sidebar state and icon names
  function toggleSidebar() {
	  if (sidebar.classList.contains("open")) {
		    sidebar.style.width = "50px";
		    mainContent.style.marginLeft = "50px";
		    openCloseBtn.innerText = "☰";

		    // Hide icon names when closing the sidebar
		    for (var i = 0; i < sidebarLinks.length; i++) {
		      sidebarLinks[i].querySelector(".hide-text").style.display = "none";
		      sidebarLinks[i].querySelector(".show-text").style.display = "none";
		    }
		  } else {
		    sidebar.style.width = "250px";
		    mainContent.style.marginLeft = "250px";
		    openCloseBtn.innerText = "☰";
		    
		    // Show icon names when opening the sidebar
		    for (var i = 0; i < sidebarLinks.length; i++) {
		      sidebarLinks[i].querySelector(".hide-text").style.display = "inline";
		      sidebarLinks[i].querySelector(".show-text").style.display = "none";
		    }
		  }
    sidebar.classList.toggle("open");
  }

  // Attach the toggle function to the button click event
  openCloseBtn.addEventListener("click", toggleSidebar);
});

