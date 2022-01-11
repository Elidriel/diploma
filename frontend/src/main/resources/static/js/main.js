const URL_CATALOG = "/catalog";
const URL_CATEGORY = "/catalog/{catalogUrl}/category/{categoryUrl}";
const URL_CART = "/cart"

$.ajax({
    url: URL_CATALOG + "/get-all",
    type: "get",
    dataType: "json",
    success: function (catalogs) {
        if (catalogs) {
            catalogs.forEach(function (catalog) {
                $("[data-catalog-links]").prepend(`<li class="nav-item">
            <a href="/catalog/${catalog.url}" class="nav-link active" aria-current="page">${catalog.name}</a>
          </li>`)
            })

        }
    }
});

$("[data-product-filter-accept]").on("click", function () {

    let catalogUrl = $("[data-catalog-url]").attr("data-catalog-url");
    let categoryUrl = $("[data-category-url]").attr("data-category-url");

    let startingPrice = $("[data-starting-price-filter]").val();
    let finalPrice = $("[data-final-price-filter]").val();

    let sizes = [];
    let colors = [];
    let brands = [];
    $("[data-size-filter]").filter(":checked").each(function () {
        sizes.push($(this).attr("data-size-filter"));
    });
    $("[data-color-filter]").filter(":checked").map(function () {
        colors.push($(this).attr("data-color-filter"));
    });
    $("[data-brand-filter]").filter(":checked").map(function () {
        brands.push($(this).attr("data-brand-filter"));
    })

    let url;
    if (categoryUrl) {
        url = URL_CATEGORY.replace("{catalogUrl}", catalogUrl).replace("{categoryUrl}", categoryUrl);
    } else {
        url = URL_CATALOG + "/" + catalogUrl;
    }

    window.location = url +
        "?startingPrice=" + startingPrice +
        "&finalPrices=" + finalPrice +
        "&sizes=" + sizes +
        "&colors=" + colors +
        "&brands=" + brands;

});

$("[data-add-product-button]").on("click", function () {

    let productId = $(this).closest("[data-product-id]").attr("data-product-id");
    let addProductInCartUrl = URL_CART + "/add?productId=" + productId;

    let $currentButton = $(this);

    $.ajax({
        url: addProductInCartUrl,
        type: "put",
        data: productId,
        dataType: "json",
        complete: function () {

            let $parentDiv = $currentButton.parent().parent();
            $parentDiv.append(getChangeProductCountInput());
            initChangeProductInput();
            initRemoveProductInput();
            $currentButton.parent().remove();

        }
    });
});

initChangeProductInput();
initRemoveProductInput();

function initRemoveProductInput() {

    let catalogUrl = $("[data-catalog-url]").attr("data-catalog-url");
    let categoryUrl = $("[data-category-url]").attr("data-category-url");

    let redirectUrl;
    if (categoryUrl) {
        redirectUrl = URL_CATEGORY.replace("{catalogUrl}", catalogUrl).replace("{categoryUrl}", categoryUrl);
    } else if (catalogUrl) {
        redirectUrl = URL_CATALOG + "/" + catalogUrl;
    } else {
        redirectUrl = URL_CART
    }

    let $productRemoveButton = $("[data-remove-product]");
    $productRemoveButton.unbind();
    $productRemoveButton.on("click", function () {
        let productId = $(this).closest("[data-product-id]").attr("data-product-id");
        let removeProductFromCartUrl = URL_CART + "/remove?productId=" + productId;

        $.ajax({
            url: removeProductFromCartUrl,
            type: "delete",
            dataType: "json",
            complete: function () {
                window.location = redirectUrl;
            }
        });

    })
}

function initChangeProductInput() {

    let $productCountInput = $("[data-count-product]");
    $productCountInput.unbind();
    $productCountInput.on("change", function () {
        let productId = $(this).closest("[data-product-id]").attr("data-product-id");
        let count = $(this).val();
        let changeProductCountInCartUrl = URL_CART + "/change?productId=" + productId + "&count=" + count;

        $.ajax({
            url: changeProductCountInCartUrl,
            type: "put",
            dataType: "json",
            complete: function () {

            }
        });

    });

}

function getChangeProductCountInput() {
    return `<div class="product-links">
                    <input data-count-product class="form-select" value="1">
                    <i data-remove-product class="far fa-trash-alt"></i>
                  </div>`;
}

