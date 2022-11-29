const Swal = require('sweetalert2')

function validateLogin(erros) {
  if (error.length > 0) {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong!',
      footer: '<a href="">Why do I have this issue?</a>'
    })

  }else{
    Swal.fire({
      icon: 'success',
      title: 'Cadastrado com sucesso',
      text: 'Your work has been saved',
      footer: '<a href="">Why do I have this issue?</a>'
    })
  }
}

module.exports = validateLogin