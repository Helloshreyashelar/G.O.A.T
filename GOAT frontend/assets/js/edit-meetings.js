// Function to fetch and display meetings from XML
function fetchAndDisplayMeetings() {

  const xmlFileUrl = '/assets/xmldocs/edit-created-meetings.xml';

  // Use jQuery to fetch the XML data
  $.ajax({
    type: 'GET',
    url: xmlFileUrl,
    dataType: 'xml',
    success: function (xml) {
      // Clear any existing meeting data in the table
      $('#meetingTable tbody').empty();

      // Parse XML and populate the table
      $(xml).find('meeting').each(function () {
        const id = $(this).find('id').text();
        const name = $(this).find('name').text();
        const seatingCapacity = $(this).find('seatingCapacity').text();
        const hours = $(this).find('hours').text(); // Added to fetch hours
        const amenities = [];

        $(this)
          .find('amenity')
          .each(function () {
            const amenityName = $(this).find('aname').text(); // Updated to use <aname>
            const costPerHour = $(this).find('costPerHour').text();
            amenities.push(`${amenityName} (${costPerHour} Credits)`);
          });

        // Append meeting data to the table
        $('#meetingTable tbody').append(`
          <tr>
            <td>${name}</td>
            <td>${seatingCapacity}</td>
            <td>${hours}</td>
            <td>${amenities.join('<br>')}</td>
            <td>
              <button class="btn btn-primary edit-meeting" data-id="${id}">Edit</button>
            </td>
          </tr>
        `);
      });
    },
  });
}

// Call the fetchAndDisplayMeetings function to load meetings initially
fetchAndDisplayMeetings();


function openEditModal(meetingId) {
  // Fetch the specific meeting data from the XML file
  $.ajax({
    type: 'GET',
    url: '/assets/xmldocs/edit-created-meetings.xml', // Replace with your XML file path
    dataType: 'xml',
    success: function (xml) {
      const meeting = $(xml).find(`meeting:has(id:contains(${meetingId}))`);

      if (meeting.length > 0) {
        const id = meeting.find('id').text();
        const name = meeting.find('name').text();
        const seatingCapacity = meeting.find('seatingCapacity').text();
        const hours = meeting.find('hours').text(); // Get the hours from XML

        // Get the selected amenities
        const selectedAmenities = [];
        meeting.find('amenity').each(function () {
          const amenityName = $(this).find('aname').text(); // Updated to 'aname'
          selectedAmenities.push(amenityName);

        });

        // Construct checkboxes for all amenities
        const amenitiesHtml = `
          <div class="form-group">
            <label>Select Amenities:</label>
            <div class="form-check">
              <input type="checkbox" class="form-check-input" id="projector" ${
                selectedAmenities.includes('Projector') ? 'checked' : ''
              }>
              <label class="form-check-label" for="projector">Projector (5 Credits)</label>
            </div>
            <div class="form-check">
              <input type="checkbox" class="form-check-input" id="wifi" ${
                selectedAmenities.includes('Wi-Fi Connection') ? 'checked' : ''
              }>
              <label class="form-check-label" for="wifi">Wi-Fi Connection (10 Credits)</label>
            </div>
            <div class="form-check">
              <input type="checkbox" class="form-check-input" id="conferenceCall" ${
                selectedAmenities.includes('Conference Call Facility') ? 'checked' : ''
              }>
              <label class="form-check-label" for="conferenceCall">Conference Call Facility (15 Credits)</label>
            </div>
            <div class="form-check">
              <input type="checkbox" class="form-check-input" id="whiteboard" ${
                selectedAmenities.includes('Whiteboard') ? 'checked' : ''
              }>
              <label class="form-check-label" for="whiteboard">Whiteboard (5 Credits)</label>
            </div>
            <div class="form-check">
              <input type="checkbox" class="form-check-input" id="waterDispenser" ${
                selectedAmenities.includes('Water Dispenser') ? 'checked' : ''
              }>
              <label class="form-check-label" for="waterDispenser">Water Dispenser (5 Credits)</label>
            </div>
            <div class="form-check">
              <input type="checkbox" class="form-check-input" id="tv" ${
                selectedAmenities.includes('TV') ? 'checked' : ''
              }>
              <label class="form-check-label" for="tv">TV (10 Credits)</label>
            </div>
            <div class="form-check">
              <input type="checkbox" class="form-check-input" id="coffeeMachine" ${
                selectedAmenities.includes('Coffee Machine') ? 'checked' : ''
              }>
              <label class="form-check-label" for="coffeeMachine">Coffee Machine (10 Credits)</label>
            </div>
          </div>
        `;

        // Populate the modal with meeting details and checkboxes, including hours
        $('#editMeetingModalLabel').text('Edit Meeting');
        $('#editMeetingModalBody').html(`
          <form>
            <div class="form-group">
              <label for="meetingName">Meeting Name:</label>
              <input type="text" class="form-control" id="meetingName" value="${name}" readonly>
            </div>
            <div class="form-group">
              <label for="seatingCapacity">Seating Capacity:</label>
              <input type="number" class="form-control" id="seatingCapacity" value="${seatingCapacity}">
            </div>
            <div class="form-group">
              <label for="hours">No of Hours:</label>
              <input type="number" class="form-control" id="hours" value="${hours}">
            </div>
            ${amenitiesHtml}
          </form>
        `);

        // Show the modal
        $('#editMeetingModal').modal('show');
      } else {
        // Handle meeting not found
        console.error(`Meeting with ID ${meetingId} not found.`);
      }
    },
    error: function () {
      // Handle error loading XML
      console.error('Error loading XML data.');
    },
  });
}

// Handle clicking the "Edit" button
$(document).on('click', '.edit-meeting', function () {
  const meetingId = $(this).data('id');
  openEditModal(meetingId);
});

// Handle clicking the "Save Changes" button
$(document).on('click', '#saveChangesButton', function () {
  // Retrieve edited data from the modal
  const editedSeatingCapacity = $('#seatingCapacity').val();
  const editedHours = $('#hours').val();
  const editedAmenities = [];

  // Assuming you've edited the meeting data
  // Collect selected amenities from checkboxes
  $('input[type="checkbox"]').each(function () {
    if ($(this).is(':checked')) {
      editedAmenities.push($(this).attr('id'));
    }
  });

  // Clear the modal content
  $('#editMeetingModalBody').empty();

  // Show a message in the modal
  const message = `
    <p>Meeting updated successfully!</p>
    <p>Seating Capacity: ${editedSeatingCapacity}</p>
    <p>No of Hours: ${editedHours}</p>
    <p>Selected Amenities: ${editedAmenities.join(', ')}</p>
  `;

  // Append the new message to the modal content
  $('#editMeetingModalBody').append(`<div class="message">${message}</div>`);

  // Hide the "Save Changes" and "Cancel" buttons
  $('#saveChangesButton').hide();
  $('.btn-secondary', $(this).closest('.modal-footer')).hide();
});

// Call the fetchAndDisplayMeetings function to load meetings initially
fetchAndDisplayMeetings();