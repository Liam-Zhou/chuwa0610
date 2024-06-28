// Switch to the 'test' database (creates it if it doesn't exist)
use test;

// Create oms_company_address collection
db.createCollection("oms_company_address");

// Insert few random entries to oms_company_address collection
db.oms_company_address.insertMany([
    {
        "address_name": "Warehouse A",
        "send_status": 1,
        "receive_status": 1,
        "name": "John Doe",
        "phone": "123-456-7890",
        "province": "Province A",
        "city": "City A",
        "region": "Region A",
        "detail_address": "123 Main St"
    },
    {
        "address_name": "Warehouse B",
        "send_status": 0,
        "receive_status": 1,
        "name": "Jane Smith",
        "phone": "234-567-8901",
        "province": "Province B",
        "city": "City B",
        "region": "Region B",
        "detail_address": "456 Elm St"
    },
    {
        "address_name": "Warehouse C",
        "send_status": 1,
        "receive_status": 0,
        "name": "Alice Johnson",
        "phone": "345-678-9012",
        "province": "Province C",
        "city": "City C",
        "region": "Region C",
        "detail_address": "789 Pine St"
    },
    {
        "address_name": "Warehouse D",
        "send_status": 0,
        "receive_status": 0,
        "name": "Bob Brown",
        "phone": "456-789-0123",
        "province": "Province D",
        "city": "City D",
        "region": "Region D",
        "detail_address": "101 Maple St"
    }
]);

// Read one entry from oms_company_address collection
print("Read one entry from oms_company_address collection:");
printjson(db.oms_company_address.findOne());

// Read all entries from oms_company_address collection
print("Read all entries from oms_company_address collection:");
db.oms_company_address.find().forEach(printjson);

// Update one entry from oms_company_address collection
db.oms_company_address.updateOne(
    { "name": "John Doe" },
    { $set: { "phone": "666-6666-8888" } }
);

// Remove one entry from oms_company_address collection
db.oms_company_address.remove({ "name": "John Doe" });

// Verify the update and delete operations
print("Read all entries from oms_company_address collection after update and delete:");
db.oms_company_address.find().forEach(printjson);