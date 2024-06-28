// Create test DB
use test

// Create oms_company_address collection
db.createCollection("oms_company_address")
db.oms_company_address.createIndex({ id: 1 }, { unique: true })

// Insert few entries to the collection
db.oms_company_address.insertMany([
   {
      id: 1,
      address_name: '123 Main St',
      send_status: 1,
      receive_status: 0,
      name: 'Mike',
      phone: '123456789',
      province: 'CA',
      city: 'San Jose',
      region: 'East',
      detail_address: 'Floor 1'
   },
   {
      id: 2,
      address_name: '572 Baine St',
      send_status: 0,
      receive_status: 0,
      name: 'James',
      phone: '132624322',
      province: 'CA',
      city: 'Fremont',
      region: 'West',
      detail_address: 'Floor 0'
   },
   {
      id: 3,
      address_name: '9021 Jackson St',
      send_status: 1,
      receive_status: 1,
      name: 'Tom',
      phone: '3409860341',
      province: 'CA',
      city: 'Cupertino',
      region: 'Central',
      detail_address: null
   },
   {
      id: 4,
      address_name: '245 Second St',
      send_status: 1,
      receive_status: 0,
      name: 'Alan',
      phone: '345572543',
      province: 'CA',
      city: 'Foster City',
      region: 'East',
      detail_address: 'Floor 3'
   }
]);

// Read one entry from the collection
db.oms_company_address.find().limit(1).next()

// Read all entries from the collection
db.oms_company_address.find()

// Update one entry from the collection
db.oms_company_address.updateOne(
   { id: 1 },
   { $set: { phone: '666-6666-8888' } }
)

// Remove one entry from the collection
db.oms_company_address.remove({ _id: 1 })