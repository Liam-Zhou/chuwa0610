use test;
db.createCollection("oms_company_address");

db.oms_company_address.insertMany([
    {
        address_name: "address1",
        send_status: 1,
        receive_status: 0,
        name: "li",
        phone: "1234567890",
        province: "Guangdong",
        city: "Guangzhou",
        region: "haizhu",
        detail_address: "123z"
    },
    {
        address_name: "address2",
        send_status: 0,
        receive_status: 1,
        name: "si",
        phone: "0987654321",
        province: "Beijing",
        city: "Beijing",
        region: "chaoyang",
        detail_address: "456z"
    }
]);

db.oms_company_address.findOne();

db.oms_company_address.find();

db.oms_company_address.updateOne(
    { name: "si" },
    { $set: { phone: "666-6666-8888" } }
);

db.oms_company_address.deleteOne({ name: "li" });
