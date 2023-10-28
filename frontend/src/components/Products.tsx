import { useContext, useEffect, useState } from "react";
import { getProducts } from "../http";
import { AuthContext } from "../App";
import _unionBy from "lodash/unionBy";
import _orderBy from "lodash/orderBy";

export default function Products() {
  const { token } = useContext(AuthContext);
  const [page, setPage] = useState(0);
  const [doneLoading, setDoneLoading] = useState(false);
  const [products, setProducts] = useState([]);

  useEffect(() => {
    getProducts(token, page).then((ps) => {
      if (!ps || !ps.length) setDoneLoading(true);
      setProducts((pr) => _orderBy(_unionBy(ps, pr, "id"), "name"));
    });
  }, [token, page]);

  return (
    <div className="max-w-7xl mx-auto space-y-10 pt-20">
      <h1 className="text-3xl font-bold">All Products</h1>
      <div className="grid grid-cols-4 gap-10">
        {products.map((product: any, i) => (
          <div key={i} className="space-y-3">
            <div className="bg-gray-200 rounded-md w-full h-44"></div>
            <p className="font-bold">{product.name}</p>
          </div>
        ))}
      </div>
      <button
        type="button"
        disabled={doneLoading}
        className="bg-violet-600 text-white rounded py-3 px-10 disabled:opacity-50"
        onClick={() => setPage((p) => p + 1)}
      >
        {doneLoading ? "That's all we have" : "Load more"}
      </button>
    </div>
  );
}
