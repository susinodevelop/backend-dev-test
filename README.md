# Similar product API

You can request following url with provided product id to request other similar products:
<http://localhost:5000/product/{productId}/similar>

Also you can use Swagger UI interface to test this api manually on the following url:
<http://localhost:5000/swagger-ui.html>

## Structure
This API was created following hexagonal architecture principles with the goal to be scalable and maintainable

## Tests
Some tests are provided with the goal to verify correct functionality of the API. The followings cases are considered:

* All requests received response with status 200
* Request for similar product ids received empty response (not be similar ids for this product)
* Request for specific product returns status 404 (not found this product)
* Exception on request for similar product ids
* Exception on request for specific product