db.createUser(
  {
    user: "bpalmer",
    pwd: "password",
    roles: [
      {
        role: "readWrite",
        db: "notas-fiscais"
      }
    ]
  }
);

db.createCollection('notaFiscal')

db.notasFiscais.insertOne({
    "autorizador":"SP",
    "autorizacao": "VERDE",
    "retornoAutorizacao": "VERDE",
    "inutilizacao": "VERDE",
    "consultaProtocolo": "VERDE",
    "statusServico": "VERDE",
    "tempoMedio":"-",
    "consultaCadastro": "VERDE",
    "recepcaoEvento": "VERDE",
    "ultimaVerificacao":"2021-05-20T23:58:24.119z",
    "created":new Date("2021-05-20T23:58:24.119z"),
    "updated":new Date("2021-05-20T23:58:24.119z"),
    "_class":"com.project.statusnotafiscal.models.NotaFiscal"
})

//db.notasFiscais.insertMany([{
//                                    "_id": ObjectId(),
//                                	"autorizador":"SP",
//                                    "autorizacao": "VERDE",
//                                    "retornoAutorizacao": "VERDE",
//                                    "inutilizacao": "VERDE",
//                                    "consultaProtocolo": "VERDE",
//                                    "statusServico": "VERDE",
//                                    "tempoMedio":"-",
//                                    "consultaCadastro": "VERDE",
//                                    "recepcaoEvento": "VERDE",
//                                    "ultimaVerificacao":("2021-05-20T23:58:24.119z"),
//                                    "created":ISODate("2021-05-20T23:58:24.119z"),
//                                    "updated":ISODate("2021-05-20T23:58:24.119z"),
//                                    "_class":"com.project.statusnotafiscal.models.NotaFiscal"
//                            },
//
//                            {
//                                "_id": ObjectId(),
//                                "autorizador":"SP",
//                                "autorizacao": "VERDE",
//                                "retornoAutorizacao": "VERDE",
//                                "inutilizacao": "VERDE",
//                                "consultaProtocolo": "VERDE",
//                                "statusServico": "VERDE",
//                                "tempoMedio":"-",
//                                "consultaCadastro": "VERDE",
//                                "recepcaoEvento": "VERDE",
//                                "ultimaVerificacao":("2022-05-20T23:58:24.119z"),
//                                "created":ISODate("2022-05-20T23:58:24.119z"),
//                                "updated":ISODate("2022-05-20T23:58:24.119z"),
//                                "_class":"com.project.statusnotafiscal.models.NotaFiscal"
//                            },
//
//                            {
//                                "_id": ObjectId(),
//                                "autorizador":"GO",
//                                "autorizacao": "AMARELHO",
//                                "retornoAutorizacao": "AMARELHO",
//                                "inutilizacao": "AMARELHO",
//                                "consultaProtocolo": "AMARELHO",
//                                "statusServico": "AMARELHO",
//                                "tempoMedio":"-",
//                                "consultaCadastro": "AMARELHO",
//                                "recepcaoEvento": "AMARELHO",
//                                "ultimaVerificacao":("2022-05-20T23:58:24.119z"),
//                                "created":ISODate("2021-05-20T23:58:24.119z"),
//                                "updated":ISODate("2021-05-20T23:58:24.119z"),
//                                "_class":"com.project.statusnotafiscal.models.NotaFiscal"
//                            },
//
//                            {
//                                "_id": ObjectId(),
//                                "autorizador":"MG",
//                                "autorizacao": "VERMELHO",
//                                "retornoAutorizacao": "VERMELHO",
//                                "inutilizacao": "VERMELHO",
//                                "consultaProtocolo": "VERMELHO",
//                                "statusServico": "VERMELHO",
//                                "tempoMedio":"-",
//                                "consultaCadastro": "VERMELHO",
//                                "recepcaoEvento": "VERMELHO",
//                                "ultimaVerificacao":("2023-05-20T23:58:24.119z"),
//                                "created":ISODate("2023-05-20T23:58:24.119z"),
//                                "updated":ISODate("2023-05-20T23:58:24.119z"),
//                                "_class":"com.project.statusnotafiscal.models.NotaFiscal"
//                            },
//
//                            {
//                                "_id": ObjectId(),
//                                "autorizador":"RJ",
//                                "autorizacao": "VERMELHO",
//                                "retornoAutorizacao": "VERMELHO",
//                                "inutilizacao": "VERMELHO",
//                                "consultaProtocolo": "VERMELHO",
//                                "statusServico": "VERMELHO",
//                                "tempoMedio":"-",
//                                "consultaCadastro": "VERMELHO",
//                                "recepcaoEvento": "VERMELHO",
//                                "ultimaVerificacao":("2023-05-20T23:58:24.119z"),
//                                "created":ISODate("2023-05-20T23:58:24.119z"),
//                                "updated":ISODate("2023-05-20T23:58:24.119z"),
//                                "_class":"com.project.statusnotafiscal.models.NotaFiscal"
//                            }])

