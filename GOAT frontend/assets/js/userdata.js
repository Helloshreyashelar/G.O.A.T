document.addEventListener('DOMContentLoaded', function () {
    // Define a function to parse and display user data from XML
    function displayUserData(xmlData) {
        const userTableBody = document.getElementById('user-table-body');
        let idCounter = 1; // Initialize a unique ID counter

        // Loop through each <user> element in the XML
        xmlData.querySelectorAll('user').forEach(function (user) {
            const name = user.querySelector('Name').textContent;
            const email = user.querySelector('Email').textContent;
            const phone = user.querySelector('Phone').textContent;
            const role = user.querySelector('Role').textContent;

            // Validate email and phone using regular expressions
            const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
            const phonePattern = /^\d{10}$/;

            if (!emailPattern.test(email) || !phonePattern.test(phone)) {
                // Skip invalid data
                return;
            }

            // Determine credits based on the role
            let credits = 0;
            if (role === 'Manager') {
                credits = 2000;
            }

            // Create a new row in the table
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
                <td>${idCounter++}</td>
                <td>${name}</td>
                <td>${email}</td>
                <td>${phone}</td>
                <td>${role}</td>
                <td>${credits}</td>
            `;

            // Append the new row to the table
            userTableBody.appendChild(newRow);
        });
    }

    // Load and parse user data from the XML file
    const xmlFilePath = '/assets/xmldocs/userdata.xml'; // Replace with the actual path to your XML file
    const xhr = new XMLHttpRequest();
    xhr.open('GET', xmlFilePath, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const parser = new DOMParser();
            const xmlDoc = parser.parseFromString(xhr.responseText, 'application/xml');
            displayUserData(xmlDoc);
        } else if (xhr.readyState === 4 && xhr.status !== 200) {
            console.error('Error loading XML data. HTTP status:', xhr.status);
        }
    };
    xhr.send();
});
