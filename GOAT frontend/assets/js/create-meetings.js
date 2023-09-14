//create-meeting js script

let remainingCredits = 2000;     

        function calculateCharges() {
  // Get the number of hours entered by the user
  const hours = parseInt(document.getElementById('hours').value);
  
  // Calculate charges based on selected amenities and hours
  const amenities = document.querySelectorAll('input[type="checkbox"]:checked');
  let totalCharges = 0;
  amenities.forEach((amenity) => {
    totalCharges += parseInt(amenity.value) * hours;
  });

  // Update remaining credits
  remainingCredits = 2000 - totalCharges;
  document.getElementById('remainingCredits').textContent = remainingCredits;
}


  function updateRemainingCredits() {
    // Update remaining credits when a checkbox is checked or unchecked
    const amenities = document.querySelectorAll('input[type="checkbox"]');
    amenities.forEach((amenity) => {
      amenity.addEventListener('change', () => {
        calculateCharges();
      });
    });
  }

  // Call the function to update remaining credits initially
  updateRemainingCredits();


      function generateUniqueId() {
        // Generate a unique ID using a timestamp
        return 'M' + Date.now();
      }
      
      
      function meetingExists(name) {  
      // Replace this URL with the actual path to your XML file
      const xmlFileUrl = '/assets/xmldocs/createdmeetings.xml';

      let exists = false;

      // Use jQuery to fetch the XML data
      $.ajax({
        type: 'GET',
        url: xmlFileUrl,
        dataType: 'xml',
        async: false, // Make a synchronous request
        success: function (xml) {
          // Check if the entered name exists in the XML
          $(xml).find('meeting').each(function () {
            const xmlName = $(this).find('name').text();
            if (xmlName === name) {
              exists = true;
              return false; // Break out of the loop
            }
          });
        },
      });

      return exists;
    }

    function saveMeeting() {
  const name = document.getElementById('name').value;
  const seatingCapacity = document.getElementById('seatingCapacity').value;
  const hours = document.getElementById('hours').value; // Get the number of hours entered by the user
  const selectedAmenities = document.querySelectorAll('input[type="checkbox"]:checked');

  // Calculate charges based on selected amenities and hours
  let totalCharges = 0;
  selectedAmenities.forEach((amenity) => {
    totalCharges += parseInt(amenity.value) * hours;
  });

  if (!name || !seatingCapacity) {
    // Display a validation error message in the message modal
    const messageModal = new bootstrap.Modal(document.getElementById('messageModal'));
    document.getElementById('messageModalLabel').textContent = 'Validation Error';
    let errorMessage = '';
    if (!name) {
      errorMessage += 'Please enter a name for the meeting.\n';
    }
    if (!seatingCapacity) {
      errorMessage += 'Please enter the seating capacity for the meeting.\n';
    }
    document.getElementById('messageMeetingId').textContent = errorMessage;
    messageModal.show();
    return;
  }

  if (selectedAmenities.length < 2) {
    // Display an error message if less than two amenities are selected
    const messageModal = new bootstrap.Modal(document.getElementById('messageModal'));
    document.getElementById('messageModalLabel').textContent = 'Validation Error';
    document.getElementById('messageMeetingId').textContent = 'Please select at least two amenities.';
    messageModal.show();
    return;
  }

  if (meetingExists(name)) {
    // Display a message if the meeting name already exists
    const messageModal = new bootstrap.Modal(document.getElementById('messageModal'));
    document.getElementById('messageModalLabel').textContent = 'Meeting Already Exists';
    document.getElementById('messageMeetingId').textContent = `Sorry, meeting already exists for user - ${name}`;
    console.log(`Meeting already exists for ${name}`);
    messageModal.show();
  } else {
    // Proceed with saving the meeting if the name doesn't exist
    // Generate a unique ID
    const uniqueId = generateUniqueId();

    // Set the message modal content including charges
    document.getElementById('messageModalLabel').textContent = 'Meeting Saved';
    document.getElementById('messageMeetingId').textContent = `Meeting saved with ID: ${uniqueId} for ${name}, Total Charges: ${totalCharges} Credits`;

    // Show the message modal
    const messageModal = new bootstrap.Modal(document.getElementById('messageModal'));
    messageModal.show();

    // You can log the meeting details to the console if needed
    console.log(`Meeting saved with ID: ${uniqueId} for ${name}, Total Charges: ${totalCharges} Credits`);

    // Reset the form
    document.getElementById('meetingRoomForm').reset();
    document.getElementById('remainingCredits').textContent = 2000;
  }
}

      // Select the "Save" button by its ID
      const saveButton = document.getElementById('saveButton');

      // Add an event listener to the button
      saveButton.addEventListener('click', saveMeeting);

      function cancelForm() {
        // Uncheck all checkboxes and reset the form
        const checkboxes = document.querySelectorAll('input[type="checkbox"]');
        checkboxes.forEach((checkbox) => {
          checkbox.checked = false;
        });

        document.getElementById('meetingRoomForm').reset();
        document.getElementById('remainingCredits').textContent = 2000;
      }

      
      