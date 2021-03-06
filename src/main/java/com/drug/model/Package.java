package com.drug.model;
// Generated Jul 29, 2016 11:56:47 AM by Hibernate Tools 5.1.0.Beta1

/**
 * PackageId generated by hbm2java
 */
public class Package implements java.io.Serializable {

  private PackageId id;

  private String productid;
  private String productndc;
  private String ndcpackagecode;
  private String packagedescription;

  public Package() {
  }

  public Package(String productid, String productndc, String ndcpackagecode, String packagedescription) {
    this.id = new PackageId(productid, ndcpackagecode);
    this.productid = productid;
    this.productndc = productndc;
    this.ndcpackagecode = ndcpackagecode;
    this.packagedescription = packagedescription;
  }

  public Package(PackageId id) {
    this.id = id;
  }

  public PackageId getId() {
    return this.id;
  }

  public void setId(PackageId id) {
    this.id = id;
  }

  public String getProductid() {
    return this.productid;
  }

  public void setProductid(String productid) {
    this.productid = productid;
  }

  public String getProductndc() {
    return this.productndc;
  }

  public void setProductndc(String productndc) {
    this.productndc = productndc;
  }

  public String getNdcpackagecode() {
    return this.ndcpackagecode;
  }

  public void setNdcpackagecode(String ndcpackagecode) {
    this.ndcpackagecode = ndcpackagecode;
  }

  public String getPackagedescription() {
    return this.packagedescription;
  }

  public void setPackagedescription(String packagedescription) {
    this.packagedescription = packagedescription;
  }

  public boolean equals(Object other) {
    if ((this == other))
      return true;
    if ((other == null))
      return false;
    if (!(other instanceof Package))
      return false;
    Package castOther = (Package) other;

    return ((this.getProductid() == castOther.getProductid()) || (this.getProductid() != null
        && castOther.getProductid() != null && this.getProductid().equals(castOther.getProductid())))
        && ((this.getProductndc() == castOther.getProductndc()) || (this.getProductndc() != null
            && castOther.getProductndc() != null && this.getProductndc().equals(castOther.getProductndc())))
        && ((this.getNdcpackagecode() == castOther.getNdcpackagecode()) || (this.getNdcpackagecode() != null
            && castOther.getNdcpackagecode() != null && this.getNdcpackagecode().equals(castOther.getNdcpackagecode())))
        && ((this.getPackagedescription() == castOther.getPackagedescription())
            || (this.getPackagedescription() != null && castOther.getPackagedescription() != null
                && this.getPackagedescription().equals(castOther.getPackagedescription())));
  }

  public int hashCode() {
    int result = 17;

    result = 37 * result + (getProductid() == null ? 0 : this.getProductid().hashCode());
    result = 37 * result + (getProductndc() == null ? 0 : this.getProductndc().hashCode());
    result = 37 * result + (getNdcpackagecode() == null ? 0 : this.getNdcpackagecode().hashCode());
    result = 37 * result + (getPackagedescription() == null ? 0 : this.getPackagedescription().hashCode());
    return result;
  }

}
