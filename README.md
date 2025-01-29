# Nexus
E-Commerce Platform

# Nexus API Documentation

## Overview
This document provides an overview of the API endpoints available in the E-Commerce Nexus application.

## Authentication APIs

| Endpoint | Method | Description | Request Body | Response |
|----------|--------|-------------|--------------|----------|
| `/api/auth/signin` | POST | Authenticates user and returns JWT | `{ "username": "user", "password": "pass" }` | User info with JWT |
| `/api/auth/signup` | POST | Registers a new user | `{ "username": "user", "email": "email@example.com", "password": "pass", "role": ["user"] }` | Success message |
| `/api/auth/username` | GET | Gets the authenticated username | None | Username |
| `/api/auth/user` | GET | Retrieves user details | None | User details |
| `/api/auth/signout` | POST | Logs out user by clearing JWT | None | Success message |

## Category APIs

| Endpoint | Method | Description | Request Body | Response |
|----------|--------|-------------|--------------|----------|
| `/api/public/categories` | GET | Retrieves all categories with pagination | None | List of categories |
| `/api/admin/categories` | POST | Creates a new category | `{ "name": "categoryName" }` | Created category |
| `/api/admin/categories/{categoryId}` | PUT | Updates an existing category | `{ "name": "newName" }` | Updated category |
| `/api/admin/categories/{categoryId}` | DELETE | Deletes a category | None | Deleted category |

## Product APIs

| Endpoint | Method | Description | Request Body | Response |
|----------|--------|-------------|--------------|----------|
| `/api/admin/categories/{categoryId}/product` | POST | Adds a product to a category | `{ "name": "productName", "price": 100 }` | Created product |
| `/api/public/products` | GET | Retrieves all products with pagination | None | List of products |
| `/api/public/categories/{categoryId}/product` | GET | Retrieves products by category | None | List of products |
| `/api/public/products/keyword/{keyword}` | GET | Searches products by keyword | None | List of products |
| `/api/admin/products/{productId}` | PUT | Updates a product | `{ "name": "newName", "price": 120 }` | Updated product |
| `/api/admin/products/{productId}` | DELETE | Deletes a product | None | Deleted product |

## Cart APIs

| Endpoint | Method | Description | Request Body | Response |
|----------|--------|-------------|--------------|----------|
| `/api/carts/products/{productId}/quantity/{quantity}` | POST | Adds product to cart | None | Updated cart |
| `/api/carts` | GET | Retrieves all carts | None | List of carts |
| `/api/carts/users/cart` | GET | Retrieves the logged-in user's cart | None | Cart details |
| `/api/carts/products/{productId}/quantity/{operation}` | PUT | Updates product quantity in cart | None | Updated cart |
| `/api/carts/{cartId}/product/{productId}` | DELETE | Removes product from cart | None | Success message |

## Address APIs

| Endpoint | Method | Description | Request Body | Response |
|----------|--------|-------------|--------------|----------|
| `/api/addresses` | POST | Adds a new address | `{ "street": "123 Main St", "city": "City", "zip": "12345" }` | Created address |
| `/api/addresses` | GET | Retrieves all addresses | None | List of addresses |
| `/api/addresses/{addressId}` | GET | Retrieves an address by ID | None | Address details |
| `/api/users/addresses` | GET | Retrieves addresses of logged-in user | None | List of user addresses |
| `/api/addresses/{addressId}` | PUT | Updates an address | `{ "street": "New St" }` | Updated address |
| `/api/addresses/{addressId}` | DELETE | Deletes an address | None | Success message |

## Order APIs

| Endpoint | Method | Description | Request Body | Response |
|----------|--------|-------------|--------------|----------|
| `/api/order/users/payments/{paymentMethod}` | POST | Places an order | `{ "addressId": 1, "pgName": "PayPal", "pgPaymentId": "123", "pgStatus": "Success", "pgResponseMessage": "Approved" }` | Order details |

