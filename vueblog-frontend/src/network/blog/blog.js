import axios from "../index";

export function getBlog(currentPage) {
  return axios.get(`/blogs?currentPage=${currentPage}`);
}

export function getBlogDetails(id) {
  return axios.get(`/blogs/${id}`);
}

export function editBlog(blogInfo) {
  return axios.post(`/blogs/edit`, blogInfo, {
    headers: {
      Authorization: localStorage.getItem("token"),
    },
  });
}
export function deleteBlog(id) {
  return axios.post(`/blogs/delete/${id}`, null, {
    headers: {
      Authorization: localStorage.getItem("token"),
    },
  });
}
