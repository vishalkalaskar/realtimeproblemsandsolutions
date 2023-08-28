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

document.addEventListener("DOMContentLoaded", function() {
	var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
	var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
		return new bootstrap.Tooltip(tooltipTriggerEl);
	});

	var sidebar = document.getElementById("mySidebar");
	var openCloseBtn = document.getElementById("openCloseBtn");

	var links = document.querySelectorAll(".sidebar a"); // Select all sidebar links
	var activeTooltip = null; // Store the active tooltip instance

	// Set the initial position to inline-flex
	links.forEach(function(link) {
		link.style.display = "inline-flex";
	});

	openCloseBtn.addEventListener("click", function() {
		var isOpen = sidebar.classList.contains("open");

		if (isOpen) {
			tooltipList.forEach(function(tooltip) {
				tooltip.enable();
			});
		} else {
			tooltipList.forEach(function(tooltip) {
				tooltip.disable();
			});
		}

		// ... (toggleSidebar logic)

		var sidebarWidth = isOpen ? 50 : 250;

		// Toggle link positioning based on sidebar width
		links.forEach(function(link) {
			if (sidebarWidth === 50) {
				link.style.display = "inline-flex";
			} else if (sidebarWidth === 250) {
				link.style.display = "block";
			}
		});
	});
	//code for tooltip remove on click
	/*links.forEach(function(link) {
		link.addEventListener("click", function(event) {
			var tooltip = bootstrap.Tooltip.getInstance(link);

			if (tooltip) {
				tooltip.dispose(); // Remove the tooltip instance

				// Add a delay before re-enabling the tooltip
				setTimeout(function() {
					tooltip = new bootstrap.Tooltip(link);
				}, 50); // Adjust the delay time as needed
			}
		});
	});
*/
});
//...thid page replace code...
function loadContent(url) {
	var dynamicContentDiv = document.getElementById("main");
	dynamicContentDiv.innerHTML = ""; // Clear previous content

	// Fetch content using AJAX
	fetch(url)
		.then(response => response.text())
		.then(content => {
			dynamicContentDiv.innerHTML = content;
		});
}

//Load dashboard content on page load
window.addEventListener('load', function() {
	loadContent('/dashboard.html');
});
