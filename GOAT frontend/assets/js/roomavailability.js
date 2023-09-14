// hard coded
const meetingRooms = [
  {
    id: 1,
    name: "Room A",
    seatingCapacity: 10,
    totalMeetings: 20,
    ratings: 4.5,
  },
  {
    id: 2,
    name: "Room B",
    seatingCapacity: 8,
    totalMeetings: 15,
    ratings: 4.0,
  },
  {
    id: 3,
    name: "Room C",
    seatingCapacity: 8,
    totalMeetings: 15,
    ratings: 4.0,
  },
  {
    id: 4,
    name: "Room D",
    seatingCapacity: 8,
    totalMeetings: 15,
    ratings: 4.0,
  },
  {
    id: 5,
    name: "Room E",
    seatingCapacity: 8,
    totalMeetings: 15,
    ratings: 4.0,
  },
  {
    id: 5,
    name: "Room E",
    seatingCapacity: 8,
    totalMeetings: 15,
    ratings: 4.0,
  },
  {
    id: 5,
    name: "Room E",
    seatingCapacity: 8,
    totalMeetings: 15,
    ratings: 4.0,
  },
  {
    id: 5,
    name: "Room E",
    seatingCapacity: 8,
    totalMeetings: 15,
    ratings: 4.0,
  },
  {
    id: 5,
    name: "Room E",
    seatingCapacity: 8,
    totalMeetings: 15,
    ratings: 4.0,
  },
];

// Function to display meeting rooms in the room-list section
function displayMeetingRooms() {
  const roomList = document.getElementById("room-list");

  meetingRooms.forEach((room) => {
    const roomItem = document.createElement("div");
    roomItem.style.minWidth = "70px";
    roomItem.style.padding = "50px 10px 20px 30px";
    roomItem.classList.add("card");
    // roomItem.style.backgroundColor = '#008374';
    
    // roomItem.classList.add("room-item");
    roomItem.innerHTML = `
           <a href="/roomdetails/${room.id}">
            <h3>${room.name}</h3>
            <p>Seating Capacity: ${room.seatingCapacity}</p>
            <p>Total Meetings: ${room.totalMeetings}</p>
            <p>Ratings: ${room.ratings}</p>
           </a>
        `;
    roomList.appendChild(roomItem);
  });
}

// Call the function to display meeting rooms when the page loads
window.addEventListener("load", displayMeetingRooms);
