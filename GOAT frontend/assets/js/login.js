document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("loginForm");
  
    loginForm.addEventListener("submit", function (e) {
      e.preventDefault();
      const userID = document.getElementById("userID").value;
      const email = document.getElementById("email").value;
  
      // Send AJAX request to check user credentials
      // Replace this part with your actual authentication logic
      fetch("/assets/xmldocs/login.xml")
        .then((response) => response.text())
        .then((xmlData) => {
          const parser = new DOMParser();
          const xmlDoc = parser.parseFromString(xmlData, "text/xml");
          const users = xmlDoc.getElementsByTagName("user");
  
          for (let i = 0; i < users.length; i++) {
            const userIdNode = users[i].getElementsByTagName("userId")[0];
            const emailNode = users[i].getElementsByTagName("email")[0];
            const userTypeNode = users[i].getElementsByTagName("userType")[0];
  
            const storedUserID = userIdNode.textContent;
            const storedEmail = emailNode.textContent;
  
            if (storedUserID === userID && storedEmail === email) {
              const userType = userTypeNode.textContent;
              redirectToDashboard(userType);
              return;
            }
          }
  
          // Display an error message if credentials are invalid
          alert("Invalid credentials. Please try again.");
        })
        .catch((error) => {
          console.error("Error fetching XML data:", error);
        });
    });
  
    function redirectToDashboard(userType) {
      switch (userType) {
        case "admin":
          window.location.href = "admin.html";
          break;
        case "manager":
          window.location.href = "manager.html";
          break;
        case "member":
          window.location.href = "member.html";
          break;
        default:
          alert("Invalid user type.");
      }
    }
  });
  