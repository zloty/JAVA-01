package com.example.rpc.client;

import com.alibaba.fastjson.parser.ParserConfig;

public class Rpcfx {
  static {
    ParserConfig.getGlobalInstance().addAccept();
  }
}
