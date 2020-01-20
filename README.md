# CatalogSearchDemo

Demo project for search catalog items faster and in a more reliable way. This is a Spring boot app written in Java. Uses inbuilt H2 database, JPA and hiberante at data layer. This is a rest API and doesnot have a UI implementation. 

Search catalog can be done based on sku, price, color, brand etc. This includes some Junit test cases also. On start of application it is assumed that app will load all avaibale tables with required data.

Product, product spec, seller, brand are entities assumed. Product spec will have product sku, Attribute name and attribute value (Key-Attribute-Value pattern).
