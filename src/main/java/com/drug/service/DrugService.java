package com.drug.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.drug.dao.PackageHome;
import com.drug.dao.ProductHome;
import com.drug.model.Product;

@Path("/drug")
public class DrugService {

  @GET
  @Path("/lista/{drugName}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getDrugsGivenDrugName(@PathParam("drugName") String drugName) {
    List<Product> results = null;
    if (drugName != null) {
      ProductHome productHome = new ProductHome();
      results = (List<Product>) productHome.findByDrugName("%" + drugName + "%");
    }
    return Response.ok(results).build();

  }

  @GET
  @Path("/list/{drugName}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getDrugsGivenDrugName(@PathParam("drugName") String drugName,
      @QueryParam("pageNum") String pageNumString, @QueryParam("pageSize") String pageSizeString,
      @QueryParam("totalSize") String totalSizeString) {
    List<Product> results = null;
    int pageNum = 1;
    int pageSize = 5;

    if (pageNumString != null)
      pageNum = Integer.parseInt(pageNumString);
    if (pageSizeString != null)
      pageSize = Integer.parseInt(pageSizeString);

    Long totalSize = 0L;

    if (drugName != null) {
      ProductHome productHome = new ProductHome();
      if (totalSizeString != null && !"0".equals(totalSizeString))
        totalSize = Long.parseLong(totalSizeString);
      else
        totalSize = productHome.countByDrugName("%" + drugName + "%", pageSize);
      if (totalSize > 0L)
        results = (List<Product>) productHome.findByDrugName("%" + drugName + "%", pageNum, pageSize);
    }
    return Response.ok(results).header("X-total-count", totalSize.toString()).build();

  }

  @GET
  @Path("/detail/{drugNDC}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getDrugsGivenNDC(@PathParam("drugNDC") String drugNDC) {
    Product product = null;
    if (drugNDC != null) {
      ProductHome productHome = new ProductHome();
      product = (Product) productHome.findByNDC(drugNDC);
    }
    return Response.ok(product).build();

  }

  @GET
  @Path("/packages/{drugNDC}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPackagesGivenProduct(@PathParam("drugNDC") String drugNDC) {
    List<Package> results = null;
    if (drugNDC != null) {
      PackageHome packageHome = new PackageHome();
      results = (List<Package>) packageHome.findByProductNdc(drugNDC);
    }
    return Response.ok(results).build();

  }
}
