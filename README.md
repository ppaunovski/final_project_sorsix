# Add dummy data on fresh start of the application
1. Get valid jwt token with one of the users:

Send request to: localhost:8080/api/auth

With the following body: {
  "email":"pavel@pavel.com",
  "password": "pp"
}

2. With the valid jwt token you got send the following requests:
   1. To add random images to all the existing properties send request to:
   
   localhost:8080/api/property-image/populate 
   
   with the header: "Authorization: Bearer ${valid_jwt}"

   2. To add random images to all the existing users send request to:
   
   localhost:8080/api/user-images/populate 
   
   with the header: "Authorization: Bearer ${valid_jwt}"\

   3. To add random bookings to random existing properties send request to:
   
   localhost:8080/api/booking/populate 
   
   with the header: "Authorization: Bearer ${valid_jwt}"

   4. To add random reviews to all existing bookings send request to:
   
   localhost:8080/api/reviews/populate 
   
   with the header: "Authorization: Bearer ${valid_jwt}"
