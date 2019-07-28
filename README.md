# PROBLEM: SALES TAXES
Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
products that are exempt. Import duty is an additional sales tax applicable on all imported goods
at a rate of 5%, with no exemptions.
When I purchase items I receive a receipt which lists the name of all the items and their price
(including tax), finishing with the total cost of the items, and the total amounts of sales taxes
paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains
(np/100 rounded up to the nearest 0.05) amount of sales tax.

It's a SpringBoot application.
It exposes a REST endpoint on POST:

/orders/receipt

Ids are stored in a H2 in-memory DB.
Since "order management" is out-of-scope for this test, product IDs are passed as array on body request (content type application/json).
For example:

[1,2,3]


Following the mapping of products and ids.
1 book at 12.49  [id = 1]
1 music CD at 14.99 [id = 2]
1 chocolate bar at 0.85 [id = 3]

1 imported box of chocolates at 10.00 [id = 4]
1 imported bottle of perfume at 47.50 [id = 5]

1 imported bottle of perfume at 27.99 [id = 6]
1 bottle of perfume at 18.99 [id = 7]
1 packet of headache pills at 9.75 [id = 8]
1 box of imported chocolates at 11.25 [id = 9]

A product has a price which can be configured to be valid in an interval of time and also can be disabled at will.
A product has a category and a category has a given tax (which can be configured like for pricing).
A product has zero, one or more attributes: each attribute could have a tax like above.