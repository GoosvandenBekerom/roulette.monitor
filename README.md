# Roulette Monitor

This project catches errors in the RabbitMQ roulette network and exposes them as a rest api

### Endpoints

- `/actuator` - Information about the state of this service
- `/api/errors` - Paginated list of all errors sorted by date